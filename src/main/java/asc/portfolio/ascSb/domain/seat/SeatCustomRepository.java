package asc.portfolio.ascSb.domain.seat;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;

import java.util.List;
import java.util.Optional;

public interface SeatCustomRepository {
    List<SeatSelectResponseDto> findSeatNumberAndSeatState(String cafeName);

    Seat findByCafeAndSeatNumber(Cafe cafeObject, int seatNumber);

}
