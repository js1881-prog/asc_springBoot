package asc.portfolio.ascSb.domain.seatreservationinfo;


import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.seat.SeatRepository;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SeatReservationInfoRepositoryTest {

    @Autowired
    SeatReservationInfoRepository seatReservationInfoRepository;

    @Autowired
    CafeRepository cafeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SeatRepository seatRepository;

    private void insertSeatReservationInfoData() {
        User user = User.builder()
                .loginId("ascasc123")
                .password("asdasd123")
                .role(UserRoleType.USER)
                .build();
        userRepository.save(user);

        Cafe cafe = Cafe.builder()
                .cafeName("tCafe_0")
                .build();
        cafeRepository.save(cafe);

        Seat seat = Seat.builder()
                .cafe(cafe)
                .seatNumber(1)
                .build();
        seatRepository.save(seat);

        Ticket ticket = Ticket.builder().build();
        ticketRepository.save(ticket);

        SeatReservationInfo seatReservationInfo = SeatReservationInfo.builder()
                .cafe(cafe)
                .user(user)
                .ticket(ticket)
                .seat(seat)
                .build();
        seatReservationInfoRepository.save(seatReservationInfo); // 지우셔도 됩니다 단순 Data Insert용 by padonan
    }

    @Test
    public void findSeatInfoByUserIdAndCafeName_테스트() {
        insertSeatReservationInfoData();

        seatReservationInfoRepository.findSeatInfoByUserIdAndCafeName("ascasc123", "tCafe_0");
    }
}
