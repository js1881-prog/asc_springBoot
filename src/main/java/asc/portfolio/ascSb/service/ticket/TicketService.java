package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.bootpay.BootpayObject;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    TicketResponseDto userValidTicket(Long id, String cafeName);

    Long saveProductToTicket(User user, BootPayOrderDto bootPayOrderDto, Orders orders);

    public List<TicketResponseDto> lookupUserTickets(String targetUserLoginId, Cafe cafe);
//
//    Long saveTicket(TicketRequestDto dto, Long id);
}
