package asc.portfolio.ascSb.domain.seat;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;

import java.util.List;

public interface SeatCustomRepository {

    public int updateAllReservedSeatStateWithFixedTermTicket();

    public int updateAllReservedSeatStateWithPartTimeTicket();

    public int updateAllReservedSeatStateWithStartTime();

    public List<Seat> getAlmostFinishedSeatListWithFixedTermTicket(Long minute);

    public List<Seat> getAlmostFinishedSeatListWithStartTime(Long minute);

    List<SeatSelectResponseDto> findSeatNumberAndSeatStateList(String cafeName);

    Seat findByCafeAndSeatNumber(Cafe cafeObject, Integer seatNumber);

    Seat findByCafeNameAndSeatNumber(String cafeName, int seatNumber);
}
