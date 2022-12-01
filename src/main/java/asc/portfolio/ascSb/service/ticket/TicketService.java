package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;

public interface TicketService {

    TicketResponseDto userTicket(Long id, String cafeName);
//
//    Long saveTicket(TicketRequestDto dto, Long id);
}
