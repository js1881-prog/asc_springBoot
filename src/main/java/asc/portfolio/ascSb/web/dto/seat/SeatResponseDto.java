package asc.portfolio.ascSb.web.dto.seat;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.seat.SeatStateType;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Getter;

@Getter
public class SeatResponseDto {

    private Long id;
    private Cafe cafe;
    private int seatNumber;
    private SeatStateType seatState;
    private User user;
    private Ticket ticketId;

    public SeatResponseDto(Seat entity) {
        this.id = entity.getId();
        this.cafe = entity.getCafe();
        this.seatNumber = entity.getSeatNumber();
        this.seatState = entity.getSeatState();
        this.user = entity.getUser();
        this.ticketId = entity.getTicket();
    }
}
