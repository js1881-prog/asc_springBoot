package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketSelectResponseDto;

import java.util.List;

public interface TicketService {

    List<TicketSelectResponseDto> userTicket(Long id);
}
