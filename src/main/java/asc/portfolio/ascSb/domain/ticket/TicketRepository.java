package asc.portfolio.ascSb.domain.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Long>, TicketCustomRepository {

    List<Ticket> findByIsValidTicketContains(TicketStateType ticketStateType);
}
