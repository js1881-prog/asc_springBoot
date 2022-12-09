package asc.portfolio.ascSb.web.dto.ticket;


import asc.portfolio.ascSb.commonenum.product.ProductNameType;
import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.ticket.TicketStateType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketForAdminResponseDto {

    private TicketStateType isValidTicket;
    private LocalDateTime fixedTermTicket; // 기간제 티켓 날짜 => fixedTermTicket - createDate 시간으로 남은기간 계산
    private Integer partTimeTicket; // 결제한 시간제 티켓시간 // 50시간, 100시간
    private Integer remainingTime; // 시간제 티켓 남은시간
    private String productLabel;
    private Integer ticketPrice;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    private String productNameType;

    private long period;

    public TicketForAdminResponseDto(Ticket ticket, String productLabel) {
        this.isValidTicket = ticket.getIsValidTicket();
        this.fixedTermTicket = ticket.getFixedTermTicket();
        this.partTimeTicket = ticket.getPartTimeTicket();
        this.remainingTime = ticket.getRemainingTime();
        this.productLabel = ticket.getProductLabel();
        this.ticketPrice = ticket.getTicketPrice();
        this.createDate = ticket.getCreateDate();
        this.modifiedDate = ticket.getModifiedDate();
        this.productNameType = productLabel;
    }
}