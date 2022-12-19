package asc.portfolio.ascSb.service.seat;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.seat.SeatRepository;
import asc.portfolio.ascSb.domain.seat.SeatStateType;
import asc.portfolio.ascSb.domain.seatreservationinfo.SeatReservationInfo;
import asc.portfolio.ascSb.domain.seatreservationinfo.SeatReservationInfoRepository;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.seat.SeatResponseDto;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final CafeRepository cafeRepository;

    private final SeatRepository seatRepository;

    private final SeatReservationInfoRepository reservationInfoRepository;

    private final TicketRepository ticketRepository;

    @Override
    public List<SeatSelectResponseDto> showCurrentAllSeatState(String cafeName) {
        return seatRepository.findSeatNumberAndSeatStateList(cafeName).stream()
                .map(SeatSelectResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public SeatResponseDto showSeatStateOne(String cafeName, Integer seatNumber) {
        if ((cafeName == null) || (seatNumber == null)) {
            log.info("NullPointer Ex : cafeName, seatNumber");
            throw new NullPointerException("NullPointer Ex : cafeName, seatNumber");
        }

        Optional<Cafe> cafeOpt = cafeRepository.findByCafeName(cafeName);
        Cafe cafe = cafeOpt.orElseThrow(() -> new IllegalArgumentException("Unknown cafe name"));
        Seat seat = seatRepository.findByCafeAndSeatNumber(cafe, seatNumber);
        if (seat.getSeatState() == SeatStateType.UNRESERVED) {
            return SeatResponseDto.setUnReservedSeat(seatNumber, seat.getSeatState());
        }

        Ticket ticket = seat.getTicket();
        SeatReservationInfo rezInfo = reservationInfoRepository.findValidSeatRezInfoByCafeNameAndSeatNumber(cafe.getCafeName(),
                seat.getSeatNumber());

        if (ticket.isValidFixedTermTicket()) {
            return SeatResponseDto.setFixedTermSeat(
                    seatNumber,
                    seat.getSeatState(),
                    rezInfo.getStartTime(),
                    rezInfo.updateTimeInUse(),
                    ticket.getFixedTermTicket());
        } else if (ticket.isValidPartTimeTicket()) {
            return SeatResponseDto.setPartTimeSeat(
                    seatNumber,
                    seat.getSeatState(),
                    rezInfo.getStartTime(),
                    rezInfo.updateTimeInUse(),
                    ticket.getPartTimeTicket(),
                    ticket.getRemainingTime());
        } else {
            log.error("IllegalState Ex. Ticket id ={}", ticket.getId());
            throw new IllegalStateException("IllegalState Ex. Ticket id =" + ticket.getId());
        }
    }

    @Override
    public Boolean exitSeat(User user) {
        log.info("Exit Seat. user = {}", user.getLoginId());

        //ReservationInfo 수정
        List<SeatReservationInfo> userRezInfo = reservationInfoRepository.findValidSeatRezInfoByLoginId(user.getLoginId());
        if (userRezInfo != null) {
            if (userRezInfo.size() > 1) {
                log.error("Valid SeatReservationInfo is Over One");
            }

            for (SeatReservationInfo info : userRezInfo) {
                //Reservation Info Exit
                Long timeInUse = info.endUsingSeat();

                //Seat Exit
                Seat findSeat = seatRepository.findByCafeNameAndSeatNumber(info.getCafeName(), info.getSeatNumber());
                findSeat.exitSeat();

                //Ticket Exit
                Ticket ticket = info.getTicket();
                if (ticket.isFixedTermTicket()) {
                    ticket.exitUsingTicket(null);
                } else {
                    ticket.exitUsingTicket(timeInUse); // 사용한 시간 startTime or timeInUse
                }
            }
        }

        // seat Table 의 User_ID Unique 를 유지하기 위해, 먼저 DB에 반영
        reservationInfoRepository.flush();
        seatRepository.flush();
        ticketRepository.flush();

        return true;
    }

    @Override
    public void exitSeatBySeatNumber(Cafe cafe, int seatNumber) {
        Seat findSeat = seatRepository.findByCafeAndSeatNumber(cafe, seatNumber);
        exitSeat(findSeat.getUser());
    }

    @Override
    public Boolean reserveSeat(User user, Integer seatNumber, Long startTime) {
        Cafe cafe = user.getCafe();
        if (cafe == null) {
            log.error("선택 된 카페가 없는 유저 입니다.");
            return false;
        }

        if ((seatNumber == null) || (startTime == null)) {
            throw new NullPointerException("NullPointerException : seatNumber, startTime");
        }

        Seat findSeat = seatRepository.findByCafeAndSeatNumber(cafe, seatNumber);
        if (findSeat == null) {
            log.error("없는 좌석입니다.");
            return false;
        } else if (findSeat.getSeatState() == SeatStateType.RESERVED) {
            log.error("이미 예약 된 좌석입니다.");
            return false;
        }

        //사용가능한 Ticket 검색 (유효성 확인도 진행)
        Optional<Ticket> ticketOpt = ticketRepository.findAvailableTicketByIdAndCafe(user.getId(), cafe.getCafeName());
        if (ticketOpt.isEmpty()) {
            log.error("사용 가능한 티켓이 없습니다.");
            return false;
        }

        //기존에 차지하고 있던 자리가 있으면 exit
        exitSeat(user);

        //seat 에 User, ticket 을 할당
        findSeat.reserveSeat(user, ticketOpt.get());

        //ReservationInfo 저장
        SeatReservationInfo reservationInfo = SeatReservationInfo.builder()
                .user(user)
                .cafe(cafe)
                .seat(findSeat)
                .ticket(ticketOpt.get())
                .startTime(startTime)
                .build();
        reservationInfoRepository.save(reservationInfo);

        log.info("좌석 예약 성공");
        return true;
    }
}
