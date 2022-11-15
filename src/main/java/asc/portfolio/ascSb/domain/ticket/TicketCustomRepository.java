package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketSelectResponseDto;

import java.util.List;

public interface TicketCustomRepository {

    List<TicketSelectResponseDto> findAvailableTicketInfo();

}