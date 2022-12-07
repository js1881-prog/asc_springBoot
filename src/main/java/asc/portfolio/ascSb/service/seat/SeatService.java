package asc.portfolio.ascSb.service.seat;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;

import java.util.List;

public interface SeatService {

    List<SeatSelectResponseDto> showCurrentSeatState(String cafeName);

    public Boolean exitSeat(User user);

    public Boolean reserveSeat(User user, Cafe cafe, int seatNumber);

    public Boolean exitSeatBySeatNumber(Cafe cafe, int seatNumber);
}
