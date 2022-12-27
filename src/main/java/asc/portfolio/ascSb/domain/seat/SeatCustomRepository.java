package asc.portfolio.ascSb.domain.seat;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;

import java.util.List;

public interface SeatCustomRepository {

    public void updateAllReservedSeatStateWithFixedTermTicket();

    public void updateAllReservedSeatStateWithPartTimeTicket();

    public void updateAllReservedSeatStateWithStartTime();
    
    List<SeatSelectResponseDto> findSeatNumberAndSeatStateList(String cafeName);

    Seat findByCafeAndSeatNumber(Cafe cafeObject, Integer seatNumber);

    Seat findByCafeNameAndSeatNumber(String cafeName, int seatNumber);
}
