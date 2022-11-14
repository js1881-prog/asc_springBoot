package asc.portfolio.ascSb.domain.ticket;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Long>, TicketCustomRepository {
}
