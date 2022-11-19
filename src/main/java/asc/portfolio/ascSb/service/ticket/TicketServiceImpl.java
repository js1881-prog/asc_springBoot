package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.web.dto.ticket.TicketRequestDto;
import asc.portfolio.ascSb.web.dto.ticket.TicketSelectResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;


    @Override
    public TicketSelectResponseDto userTicket(Long id, String cafeName) {
        LocalDateTime dateTime = LocalDateTime.now();

        List<TicketSelectResponseDto> dtoList = ticketRepository.findAvailableTicketInfoById(id, cafeName);
        TicketSelectResponseDto dto = dtoList.get(0);
        Duration termData = Duration.between(dto.getFixedTermTicket(), dateTime);
        dto.setPeriod(termData);
        return dto;

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
