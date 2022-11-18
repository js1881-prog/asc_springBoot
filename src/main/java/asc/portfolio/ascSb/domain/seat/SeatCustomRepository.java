package asc.portfolio.ascSb.domain.seat;

import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;

import java.util.List;

public interface SeatCustomRepository {
    List<SeatSelectResponseDto> findSeatNumberAndSeatState();
}
