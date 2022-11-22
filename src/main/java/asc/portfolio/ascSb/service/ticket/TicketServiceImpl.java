package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.web.dto.ticket.TicketSelectResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public TicketSelectResponseDto userTicket(Long id, String cafeName) throws IndexOutOfBoundsException {
        LocalDateTime dateTime = LocalDateTime.now();
        try {
            List<TicketSelectResponseDto> dtoList = ticketRepository.findAvailableTicketInfoById(id, cafeName);
            TicketSelectResponseDto dto = dtoList.get(0);
            long termData = Duration.between(dateTime, dto.getFixedTermTicket()).toMinutes();
            dto.setPeriod(termData);
            return dto;
        } catch (IndexOutOfBoundsException exception) {
            log.info("티켓이 존재하지 않습니다.");
        }
        return null;
    }

//    @Override
//    public Long saveTicket(TicketRequestDto dto, Long id) {
//
//        List<TicketSelectResponseDto> ticketCheck = ticketRepository.findAvailableTicketInfoById(id);
//        if (ticketCheck != null) {
//            log.info("이미 유효한 티켓이 존재합니다. ticketId={}", id);
//        }
//        return 0L;
//    }
}
