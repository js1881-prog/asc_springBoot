package asc.portfolio.ascSb.service.seat;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.seat.SeatRepository;
import asc.portfolio.ascSb.domain.seat.SeatStateType;
import asc.portfolio.ascSb.domain.user.User;
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

    private final SeatRepository seatRepository;

    @Override
    public List<SeatSelectResponseDto> showCurrentSeatState(String cafeName) {
        return seatRepository.findSeatNumberAndSeatState(cafeName).stream()
                .map(SeatSelectResponseDto::new)
                .collect(Collectors.toList());
    }

    //TODO reserveSeat, exitSeat 중 SeatReservationInfo 처리
    @Override
    public Boolean exitSeat(User user) {
        log.info("Exit Seat. user = {}", user.getLoginId());
        Optional<Seat> findSeatOpt = seatRepository.findByUser(user);

        if (findSeatOpt.isEmpty()) {
            log.error("No seat where the user sat");
            return false;
        }

        findSeatOpt.ifPresent(s -> s.exitSeat());
        // seat Table 의 User_ID Unique 를 유지하기 위해, 먼저 반영
        seatRepository.flush();

        return true;
    }

    @Override
    public Boolean reserveSeat(User user, Cafe cafe, int seatNumber) {

        Seat findSeat = seatRepository.findByCafeAndSeatNumber(cafe, seatNumber);
        if (findSeat == null) {
            return false;
        } else if (findSeat.getSeatState() == SeatStateType.RESERVED) {
            return false;
        }

        //ticket 유효성 확인

        //기존에 차지하고 있던 자리가 있으면 exit
        exitSeat(user);

        findSeat.reserveSeat(user);

        return true;
    }
}
