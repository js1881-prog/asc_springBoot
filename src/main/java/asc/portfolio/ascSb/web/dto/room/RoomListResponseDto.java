package asc.portfolio.ascSb.web.dto.room;
import asc.portfolio.ascSb.domain.room.Room;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Getter;

@Getter
public class RoomListResponseDto {

    private Long id;
    private int seatNumber;
    private String seatState;
    private User loginId;
    private Ticket ticketId;

    public RoomListResponseDto(Room entity) {
        this.id = entity.getId();
        this.seatNumber = entity.getSeatNumber();
        this.seatState = entity.getSeatState();
//        this.loginId = entity.getLoginId();
//        this.ticketId = entity.getTicketId();
    }
}
