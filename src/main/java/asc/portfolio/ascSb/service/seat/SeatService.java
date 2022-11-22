package asc.portfolio.ascSb.service.seat;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;

import java.util.List;

public interface SeatService {

    List<SeatSelectResponseDto> showCurrentSeatState(String cafeName);
}
