package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import asc.portfolio.ascSb.web.dto.ticket.TicketForAdminResponseDto;
import asc.portfolio.ascSb.web.dto.ticket.TicketForUserResponseDto;

import java.util.List;

public interface TicketService {

    TicketForUserResponseDto userValidTicket(Long id, String cafeName);

    Long saveProductToTicket(User user, BootPayOrderDto bootPayOrderDto, Orders orders);

    public List<TicketForUserResponseDto> lookupUserTickets(String targetUserLoginId, Cafe cafe);
//
//    Long saveTicket(TicketRequestDto dto, Long id);

    TicketForAdminResponseDto adminLookUpUserValidTicket(String userLoginID, String cafeName);

    void setInvalidTicket(String productLabel);

    public Long updateAllValidTicketState();

    List<Ticket> allInvalidTicketInfo();

    void deleteInvalidTicket(List<Ticket> tickets);


}
