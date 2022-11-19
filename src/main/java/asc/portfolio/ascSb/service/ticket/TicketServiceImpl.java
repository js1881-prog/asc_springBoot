package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.web.dto.ticket.TicketSelectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;


    @Override
    public TicketSelectResponseDto userTicket(Long id) {
        LocalDateTime dateTime = LocalDateTime.now();

        List<TicketSelectResponseDto> dtoList = ticketRepository.findAvailableTicketInfoById(id);
        TicketSelectResponseDto dto = dtoList.get(0);
        Duration termData = Duration.between(dto.getFixedTermTicket(), dateTime);
        dto.setPeriod(termData);
        return dto;
    }
}
