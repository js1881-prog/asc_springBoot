package asc.portfolio.ascSb.service.expiredticket;

import asc.portfolio.ascSb.domain.expiredticket.ExpiredTicket;
import asc.portfolio.ascSb.domain.expiredticket.ExpiredTicketRepository;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.web.dto.expiredticket.ExpiredTicketForTransferDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ExpiredTicketServiceImpl implements ExpiredTicketService {

    private final ExpiredTicketRepository expiredTicketRepository;

    // invalid TicketList 를 ExpiredTicket Table에 insert
    @Override
    public boolean transferInvalidTicket(List<Ticket> ticket) {
        List<ExpiredTicket> expiredTickets = ticket.stream()
                .map(ExpiredTicketForTransferDto::new)
                .map(ExpiredTicketForTransferDto::toEntity)
                .collect(Collectors.toList());
        if (expiredTickets.isEmpty()) {
            return false;
        }
        expiredTicketRepository.saveAll(expiredTickets);
        return true;
    }
}

