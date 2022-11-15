package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketSelectResponseDto;

import java.util.List;

public interface TicketCustomRepository {

    List<TicketSelectResponseDto> findAvailableTicketInfoById(Long id);

    Long verifyTicket(); // update가 진행된 isDeprecatedTicket 갯수를 return
}