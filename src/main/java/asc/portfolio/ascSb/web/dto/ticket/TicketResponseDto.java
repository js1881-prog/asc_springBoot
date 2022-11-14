package asc.portfolio.ascSb.web.dto.ticket;

import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Getter;

import java.sql.Date;

@Getter
public class TicketResponseDto {

    private Long id;
    private User user;
    private String isDeprecatedTicket;
    private Integer ticketPrice;
    private Date fixedTermTicket;
    private Integer partTimeTicket;
    private Integer remainingTime;

    public TicketResponseDto(Ticket entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.isDeprecatedTicket = entity.getIsDeprecatedTicket();
        this.ticketPrice = entity.getTicketPrice();
        this.fixedTermTicket = entity.getFixedTermTicket();
        this.partTimeTicket = entity.getPartTimeTicket();
        this.remainingTime = entity.getRemainingTime();
    }
}
