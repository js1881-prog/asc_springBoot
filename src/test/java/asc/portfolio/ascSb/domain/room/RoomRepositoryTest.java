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

    User user;
    Cafe cafe;
    Ticket ticket;

    @BeforeEach
    public void insert_TestData() {
        //User Test Data
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

        //Cafe Test Data
        cafe = Cafe.builder().build();

        //ticket Test Data
        ticket = Ticket.builder().build();
    }

    @Test
    public void Room_좌석생성기() {

        for(int i=0; i < 40; i ++) {
            Room room = new Room();
            room.setSeatNumber(i);
            if (i % 2 == 0) {
                room.setSeatState("Y");
            } else {
                room.setSeatState("N");
            }

            room.setCafe(cafe);
            room.setUser(user);
            room.setTicket(ticket);

            Room roomResult = roomRepository.save(room);
        }
    }
}
