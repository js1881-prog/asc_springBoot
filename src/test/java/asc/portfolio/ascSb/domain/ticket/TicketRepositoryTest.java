package asc.portfolio.ascSb.domain.ticket;


import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
public class TicketRepositoryTest {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CafeRepository cafeRepository;

    User user;
    Cafe cafe;

    @BeforeEach
    public void insert_TestData() {
        String loginId = "ascUser1234";
        String password = "ascUser1234";
        String email = "asc@gmail.com";
        String name = "asc";

        user = User.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .name(name)
                .role(UserRoleType.USER)
                .build();

        userRepository.save(user);

        cafe = Cafe.builder()
                .cafeName("testCafe")
                .cafeArea("testArea")
                .build();

        cafeRepository.save(cafe);
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
        ticket.setCafe(cafe);

        //when
        Ticket ticketResult = ticketRepository.save(ticket);

         //then
        assertThat(ticketResult.getFixedTermTicket().isEqual(date));
    }

//    @Test
//    public void ticket_갱신테스트() {
//
//        ticketRepository.findAvailableTicketInfoById(1L);
//    }
}
