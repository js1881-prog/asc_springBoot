package asc.portfolio.ascSb.web.dto.seat;
import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.seat.SeatStateType;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Getter;

@Getter
public class SeatListResponseDto {

    private Long id;
    private int seatNumber;
    private SeatStateType seatState;
    private User user;
    private Ticket ticket;

    public SeatListResponseDto(Seat entity) {
        this.id = entity.getId();
        this.seatNumber = entity.getSeatNumber();
        this.seatState = entity.getSeatState();
        this.user = entity.getUser();
        this.ticket = entity.getTicket();
    }
}
