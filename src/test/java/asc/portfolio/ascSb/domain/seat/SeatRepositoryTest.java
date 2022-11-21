package asc.portfolio.ascSb.domain.seat;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SeatRepositoryTest {

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    CafeRepository cafeRepository;

    private void setSeatTestData() {
        Cafe cafe = Cafe.builder()
                .cafeName("testData_서울")
                .build();

        cafeRepository.save(cafe);

        String loginId = "ascUser1234";
        String password = "ascUser1234";
        String email = "asc@gmail.com";
        String name = "asc";

        User user = User.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .name(name)
                .role(UserRoleType.USER)
                .build();

        userRepository.save(user);

        for(int i=0; i < 40; i ++) {

            Seat seat = Seat.builder()
                    .seatNumber(i)
                    .cafe(cafe)
                    .build();

            if (i % 2 == 0) {
                seat.reserveSeat(user);
            }

            seatRepository.save(seat);
        }
    }

    @Test
    public void Seat_좌석생성기() {
        setSeatTestData();
    }
}
