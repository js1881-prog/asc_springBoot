package asc.portfolio.ascSb.web.dto.ticket;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.ticket.TicketStateType;
import asc.portfolio.ascSb.domain.user.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TicketRequestDto {

    private Cafe cafe;
    private User user;
    private TicketStateType isValidTicket;
    private Integer ticketPrice;
    private LocalDateTime fixedTermTicket;
    private Long partTimeTicket;
    private Long remainingTime;
    @NotNull
    private String productLabel;

    @Builder
    public TicketRequestDto(Cafe cafe, User user, TicketStateType isValidTicket, Integer ticketPrice, LocalDateTime fixedTermTicket,
                            Long partTimeTicket, Long remainingTime, String productLabel)
    {
        this.cafe = cafe;
        this.user = user;
        this.isValidTicket = isValidTicket;
        this.ticketPrice = ticketPrice;
        this.fixedTermTicket = fixedTermTicket;
        this.partTimeTicket = partTimeTicket;
        this.remainingTime = remainingTime;
        this.productLabel = productLabel;
    }

    public Ticket toEntity() {
        return Ticket.builder()
                .user(user)
                .cafe(cafe)
                .isValidTicket(isValidTicket)
                .ticketPrice(ticketPrice)
                .fixedTermTicket(fixedTermTicket)
                .partTimeTicket(partTimeTicket)
                .remainingTime(remainingTime)
                .productLabel(productLabel)
                .build();
    }
}
