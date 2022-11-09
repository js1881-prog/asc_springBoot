package asc.portfolio.ascSb.web.dto.room;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.room.Room;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Getter;

@Getter
public class RoomResponseDto {

    private Long id;
    private Cafe cafeId;
    private int seatNumber;
    private String seatState;
    private User loginId;
    private Ticket ticketId;

    public RoomResponseDto(Room entity) {
        this.id = entity.getId();
//        this.cafeId = entity.getCafeId();
        this.seatNumber = entity.getSeatNumber();
        this.seatState = entity.getSeatState();
//        this.loginId = entity.getLoginId();
//        this.ticketId = entity.getTicketId();
    }
}
