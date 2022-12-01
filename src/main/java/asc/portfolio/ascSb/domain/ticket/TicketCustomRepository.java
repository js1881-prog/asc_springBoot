package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;

import java.util.List;

public interface TicketCustomRepository {

    TicketResponseDto findAvailableTicketInfoById(Long id, String cafeName);

    Long verifyTicket(); // update가 진행된 isDeprecatedTicket 갯수를 return

    List<TicketResponseDto> findAllTicketInfoByLoginIdAndCafe(String loginId, Cafe cafe);
}