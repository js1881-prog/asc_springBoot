package asc.portfolio.ascSb.web.dto.ticket;

import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TicketRequestDto {

    private Long id;
    private User user;
    private String isDeprecatedTicket;
    private Integer ticketPrice;
    private LocalDateTime fixedTermTicket;
    private Integer partTimeTicket;
    private Integer remainingTime;

    @Builder
    public TicketRequestDto(Long id, User user, String isDeprecatedTicket, Integer ticketPrice,
                                      LocalDateTime fixedTermTicket, Integer partTimeTicket, Integer remainingTime)
    {
        this.id = id;
        this.user = user;
        this.isDeprecatedTicket = isDeprecatedTicket;
        this.ticketPrice = ticketPrice;
        this.fixedTermTicket = fixedTermTicket;
        this.partTimeTicket = partTimeTicket;
        this.remainingTime = remainingTime;
    }

    public Ticket toEntity() {
        return Ticket.builder()
                .user(user)
                .isDeprecatedTicket(isDeprecatedTicket)
                .ticketPrice(ticketPrice)
                .fixedTermTicket(fixedTermTicket)
                .partTimeTicket(partTimeTicket)
                .remainingTime(remainingTime)
                .build();
    }
}
