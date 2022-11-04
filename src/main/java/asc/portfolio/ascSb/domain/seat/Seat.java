package asc.portfolio.ascSb.domain.seat;
import asc.portfolio.ascSb.domain.BaseTimeEntity;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "SEAT")
public class Seat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEAT_ID", nullable = false)
    private Long id; // 좌석번호 X 사용구분을 위한 table 입니다 ex) 몇시,몇분에 어느좌석에 어떤user가 몇시간을 사용했다~

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    @Column(name = "SN")
    private int seatNumber;

    @Column(name = "T_IU")
    private int timeInUse; // 사용 결제한 시간

    @Column(name = "IS_N_ES")
    @ColumnDefault("true")
    private boolean isNotEmptySeat;

}
