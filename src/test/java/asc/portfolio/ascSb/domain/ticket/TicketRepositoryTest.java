package asc.portfolio.ascSb.domain.ticket;

import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketRepositoryTest {

    @Autowired
    TicketRepository ticketRepository;

    @Test
    public void ticketInsert() {

       String isDeprecatedTicket = "N";
       Integer ticketPrice = 3000;
       Date fixedTermTicket = new Date(20221111);
       Integer partTimeTicket = 50;
       Integer remainingTime = 50;

       String ticketBuilderToString = Ticket.builder()
                .isDeprecatedTicket(isDeprecatedTicket)
                .ticketPrice(ticketPrice)
                .fixedTermTicket(fixedTermTicket)
                .partTimeTicket(partTimeTicket)
                .remainingTime(remainingTime)
                .toString();

       Ticket ticketBuilder = Ticket.builder()
               .isDeprecatedTicket(isDeprecatedTicket)
               .ticketPrice(ticketPrice)
               .fixedTermTicket(fixedTermTicket)
               .partTimeTicket(partTimeTicket)
               .remainingTime(remainingTime)
               .build();

       ticketRepository.save(ticketBuilder);
       List<Ticket> ticketList = ticketRepository.findAll();

    }
}
