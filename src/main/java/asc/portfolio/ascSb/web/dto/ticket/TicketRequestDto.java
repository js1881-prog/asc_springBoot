package asc.portfolio.ascSb.web.dto.ticket;

import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.ticket.TicketStateType;
import asc.portfolio.ascSb.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TicketRequestDto {

    private Long id;
    private User user;
    private TicketStateType isValidTicket;
    private Integer ticketPrice;
    private LocalDateTime fixedTermTicket;
    private Integer partTimeTicket;
    private Integer remainingTime;

    @Builder
    public TicketRequestDto(Long id, User user, TicketStateType isValidTicket, Integer ticketPrice,
                                      LocalDateTime fixedTermTicket, Integer partTimeTicket, Integer remainingTime)
    {
        this.id = id;
        this.user = user;
        this.isValidTicket = isValidTicket;
        this.ticketPrice = ticketPrice;
        this.fixedTermTicket = fixedTermTicket;
        this.partTimeTicket = partTimeTicket;
        this.remainingTime = remainingTime;
    }

    public Ticket toEntity() {
        return Ticket.builder()
                .user(user)
                .isValidTicket(isValidTicket)
                .ticketPrice(ticketPrice)
                .fixedTermTicket(fixedTermTicket)
                .partTimeTicket(partTimeTicket)
                .remainingTime(remainingTime)
                .build();
    }
}
