package asc.portfolio.ascSb.domain.ticket;


import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
public class TicketRepositoryTest {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    public void insert_TestData() {
        String password = "ascUser1234";
        String email = "asc@gmail.com";
        String name = "asc";
        String nickname = "asc";

        user = User.builder()
                .password(password)
                .email(email)
                .name(name)
                .nickname(nickname)
                .build();

        userRepository.save(user);
    }

    @Test
    public void ticket_생성기() {
        //given
        LocalDateTime date = LocalDateTime.now();

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setIsDeprecatedTicket("N");
        ticket.setTicketPrice(3000);
        ticket.setFixedTermTicket(LocalDateTime.now());
        ticket.setPartTimeTicket(0);
        ticket.setRemainingTime(0);
        //when
        Ticket ticketResult = ticketRepository.save(ticket);

         //then
        assertThat(ticketResult.getFixedTermTicket().isEqual(date));
    }

    @Test
    public void ticket_갱신테스트() {

        ticketRepository.findAvailableTicketInfoById(1L);
    }
}
