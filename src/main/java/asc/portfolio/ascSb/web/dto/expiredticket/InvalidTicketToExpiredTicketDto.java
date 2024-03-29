package asc.portfolio.ascSb.web.dto.expiredticket;

import asc.portfolio.ascSb.domain.expiredticket.ExpiredTicket;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InvalidTicketToExpiredTicketDto {

    private String productLabel;

    private Long cafeId;

    private Long userId;

    // for transfer InvalidTicket
    public InvalidTicketToExpiredTicketDto(Ticket ticket) {
        this.productLabel = ticket.getProductLabel();
        this.cafeId = ticket.getCafe().getId();
        this.userId = ticket.getUser().getId();
    }

    public ExpiredTicket toEntity() {
        return ExpiredTicket.builder()
                .productLabel(productLabel)
                .cafeId(cafeId)
                .userId(userId)
                .build();
    }
}
