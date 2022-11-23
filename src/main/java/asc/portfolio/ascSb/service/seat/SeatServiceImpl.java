package asc.portfolio.ascSb.service.seat;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.seat.SeatRepository;
import asc.portfolio.ascSb.domain.seat.SeatStateType;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        seatRepository.findByUser(user).ifPresent(s -> s.exitSeat());
        seatRepository.flush();

        findSeat.reserveSeat(user);

        return true;
    }
}
