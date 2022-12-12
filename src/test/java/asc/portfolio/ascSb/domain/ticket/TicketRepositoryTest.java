package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.config.querydslconfig.TestQueryDslConfig;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.cafe.QCafe;
import asc.portfolio.ascSb.domain.ticket.QTicket;
import asc.portfolio.ascSb.domain.user.QUser;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestQueryDslConfig.class)
public class TicketRepositoryTest {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CafeRepository cafeRepository;

    @Autowired
    TestEntityManager testEntityManager;

    User user;
    Cafe cafe;

    //@BeforeEach
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

    @DisplayName("특정user의 특정cafe의 ticket을 조회")
    @Test
    void queryDsl_findTicketByUserIdAndCafeId() {
        EntityManager entityManager = testEntityManager.getEntityManager();

        JPAQuery<Ticket> query = new JPAQuery<>(entityManager);
        QTicket qTickets = new QTicket("t");
        QUser qUser = new QUser("u");
        QCafe qCafe = new QCafe("q");

        List<Ticket> tickets = query
                .select(qTickets)
                .from(qTickets)
                .rightJoin(qTickets.user, qUser)
                .on(qTickets.user.id.eq(qUser.id))
                .where(qTickets.cafe.id.eq(1L))
                .fetch();

        System.out.println(tickets);

        assertThat(tickets).hasSize(1);
    }

    @DisplayName("유효기간이 지난 FixedTerm Ticket 의 유효성 체크")
    @Test
    public void checkFixedTermTicketValidation() {
      //given
        // 유효기간이 지난 Valid 상태의 FixedTerm Ticket 생성
        Ticket ticket = Ticket.builder()
                .isValidTicket(TicketStateType.VALID)
                .fixedTermTicket(LocalDateTime.now().minusMinutes(1L))
                .build();

        ticketRepository.save(ticket);

      //when
        boolean isValid = ticket.isValidFixedTermTicket();

        //then
        assertThat(isValid).isFalse();
        assertThat(ticket.getIsValidTicket()).isEqualTo(TicketStateType.INVALID);
    }

    @DisplayName("남은 시간이 소진된 PartTime Ticket 의 유효성 체크")
    @Test
    public void checkPartTimeTicketValidation_1() {
        //given
        Long testTime = 10 * 60L;
        // 유효기간이 지난 Valid 상태의 FixedTerm Ticket 생성
        Ticket ticket = Ticket.builder()
                .isValidTicket(TicketStateType.VALID)
                .partTimeTicket(testTime)
                .remainingTime(testTime)
                .build();

        ticketRepository.save(ticket);

        //when
        ticket.exitUsingTicket(testTime); //remainingTime 전체 사용

        //then
        assertThat(ticket.getIsValidTicket()).isEqualTo(TicketStateType.INVALID);
    }

    @DisplayName("남은 시간이 없는 PartTime Ticket 의 유효성 체크")
    @Test
    public void checkPartTimeTicketValidation_2() {
        //given
        Long testTime = 10 * 60L;
        // 유효기간이 지난 Valid 상태의 FixedTerm Ticket 생성
        Ticket ticket = Ticket.builder()
                .isValidTicket(TicketStateType.VALID)
                .partTimeTicket(testTime)
                .remainingTime(0L)
                .build();

        ticketRepository.save(ticket);

        //when
        boolean isValid = ticket.isValidPartTimeTicket();

        //then
        assertThat(isValid).isFalse();
        assertThat(ticket.getIsValidTicket()).isEqualTo(TicketStateType.INVALID);
    }
}
