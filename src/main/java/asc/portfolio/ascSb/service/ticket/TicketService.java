package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;

import java.util.List;

public interface TicketService {

    TicketResponseDto userTicket(Long id, String cafeName);

    public List<TicketResponseDto> lookupUserTickets(String targetUserLoginId, Cafe cafe);
//
//    Long saveTicket(TicketRequestDto dto, Long id);
}
