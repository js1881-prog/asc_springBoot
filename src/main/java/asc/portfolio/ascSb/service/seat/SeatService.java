package asc.portfolio.ascSb.service.seat;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.seat.SeatResponseDto;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;

import java.util.List;

public interface SeatService {

    List<SeatSelectResponseDto> showCurrentAllSeatState(String cafeName);

    public SeatResponseDto showSeatStateOne(String cafeName, Integer seatNumber);

    public Boolean exitSeat(User user);

    public Boolean reserveSeat(User user, Integer seatNumber, Long startTime);

    public void exitSeatBySeatNumber(Cafe cafe, int seatNumber);

    public void updateAllSeatState();
}
