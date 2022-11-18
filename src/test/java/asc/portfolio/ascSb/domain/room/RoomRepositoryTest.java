package asc.portfolio.ascSb.domain.room;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    CafeRepository cafeRepository;

    private void setRoomTestData() {
        Cafe cafe = Cafe.builder()
                .cafeName("testData_서울")
                .build();

        cafeRepository.save(cafe);

        String loginId = "ascUser1234";
        String password = "ascUser1234";
        String email = "asc@gmail.com";
        String name = "asc";
        String nickname = "asc";

        User user = User.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .name(name)
                .nickname(nickname)
                .build();

        userRepository.save(user);

        for(int i=0; i < 40; i ++) {

            Room room = Room.builder()
                    .seatNumber(i)
                    .cafe(cafe)
                    .build();

            if (i % 2 == 0) {
                room.reserveRoom(user);
            }

            roomRepository.save(room);
        }
    }

    @Test
    public void Room_좌석생성기() {
        setRoomTestData();
    }
}
