package asc.portfolio.ascSb.service.expiredticket;

import asc.portfolio.ascSb.domain.ticket.Ticket;

import java.util.List;

public interface ExpiredTicketService {

    boolean transferInvalidTicket(List<Ticket> ticketList);
}
