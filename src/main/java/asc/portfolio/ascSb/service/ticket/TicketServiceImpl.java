package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.web.dto.ticket.TicketSelectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;


    @Override
    public List<TicketSelectResponseDto> userTicket(Long id) {
        return ticketRepository.findAvailableTicketInfoById(id)
                .stream()
                .map(TicketSelectResponseDto::new)
                .collect(Collectors.toList());
    }
}
