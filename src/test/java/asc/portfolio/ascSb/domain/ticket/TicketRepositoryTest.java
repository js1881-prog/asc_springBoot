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
        String loginId = "insetTest1234";
        String password = "insetTest1234";
        String email = "insetTest1234@gmail.com";
        String name = "insetTest1234";

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

//    @Test
//    public void ticket_생성기() {
//        //given
//        LocalDateTime date = LocalDateTime.now();
//
//        Ticket ticket = Ticket.builder()
//                .cafe(cafeRepository.getReferenceById(1L))
//                .user(userRepository.getReferenceById(8L))
//                .isValidTicket(TicketStateType.VALID)
//                .ticketPrice(3000)
//                .fixedTermTicket(LocalDateTime.now())
//                .partTimeTicket(0)
//                .remainingTime(0)
//                .build();
//
//        Ticket ticketResult = ticketRepository.save(ticket);
//    }

//    @Test
//    public void ticket_갱신테스트() {
//
//        ticketRepository.findAvailableTicketInfoById(1L);
//    }
}
