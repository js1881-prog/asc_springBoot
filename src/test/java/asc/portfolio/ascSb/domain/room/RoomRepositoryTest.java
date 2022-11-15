package asc.portfolio.ascSb.domain.room;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    CafeRepository cafeRepository;

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

            room.setCafeId(cafeRepository.getReferenceById(1L));
            room.setLoginId(userRepository.getReferenceById(1L));
            room.setTicketId(ticketRepository.getReferenceById(2L));

            Room roomResult = roomRepository.save(room);

        }
    }
}
