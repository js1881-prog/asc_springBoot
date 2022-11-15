package asc.portfolio.ascSb.domain.ticket;


import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class TicketRepositoryTest {



    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    public void ticket_생성기() {

        LocalDateTime date = LocalDateTime.now();

        Ticket ticket = new Ticket();
        ticket.setUser(userRepository.getReferenceById(1L));
        ticket.setIsDeprecatedTicket("N");
        ticket.setTicketPrice(3000);
        ticket.setFixedTermTicket(LocalDateTime.now());
        ticket.setPartTimeTicket(0);
        ticket.setRemainingTime(0);

        Ticket ticketResult = ticketRepository.save(ticket);

        // then
        assertThat(ticketResult.getFixedTermTicket().isEqual(date));

    }
}
