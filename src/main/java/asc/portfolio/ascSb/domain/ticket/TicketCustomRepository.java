package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;

public interface TicketCustomRepository {

    TicketResponseDto findAvailableTicketInfoById(Long id, String cafeName);

    Long verifyTicket(); // update가 진행된 isDeprecatedTicket 갯수를 return


}