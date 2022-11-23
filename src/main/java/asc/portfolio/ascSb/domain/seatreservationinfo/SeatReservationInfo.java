package asc.portfolio.ascSb.domain.seatreservationinfo;
import asc.portfolio.ascSb.domain.BaseTimeEntity;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "SEAT_REZ_INFO")
public class SeatReservationInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "S_ID", nullable = false)
    private Long id; // 좌석번호 X 사용구분을 위한 table 입니다 ex) 몇시,몇분에 어느좌석에 어떤 user가 몇시간을 사용했다~

    @Enumerated(EnumType.STRING)
    private SeatReservationInfoType isValid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_ID")
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; // 어떤 유저가

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "T_ID") // 어떤 ticket을
    private Ticket ticket;

    @Column(name = "S_N") // 어느 좌석에서
    private Integer seatNumber;

    @Column(name = "S_T")
    private Integer startTime;

    @Column(name = "T_IU")
    private Integer timeInUse; // 실제 사용한 시간 ( 이용종료시 )
}
