package asc.portfolio.ascSb.web.dto.room;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.room.Room;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Getter;

@Getter
public class RoomResponseDto {

    private Long id;
    private Cafe cafe;
    private int seatNumber;
    private String seatState;
    private User user;
    private Ticket ticketId;

    public RoomResponseDto(Room entity) {
        this.id = entity.getId();
        this.cafe = entity.getCafe();
        this.seatNumber = entity.getSeatNumber();
        this.seatState = entity.getSeatState().name();
        this.user = entity.getUser();
        this.ticketId = entity.getTicket();
    }
}
