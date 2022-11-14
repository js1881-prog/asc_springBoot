package asc.portfolio.ascSb.domain.seat;


import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SeatRepositoryTest {

    @Autowired
    SeatRepository seatRepository;

    @Test
    public void seatInsert() {

        Cafe cafeId;
        User user;
        Ticket ticket;
        int seatNumber;
        int timeInUse;

    }
}
