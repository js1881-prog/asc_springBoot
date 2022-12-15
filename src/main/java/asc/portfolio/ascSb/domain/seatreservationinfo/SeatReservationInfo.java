package asc.portfolio.ascSb.domain.seatreservationinfo;
import asc.portfolio.ascSb.domain.BaseTimeEntity;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.seat.Seat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "SEAT_REZ_INFO")
public class SeatReservationInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "S_ID", nullable = false)
    private Long id; // 사용구분을 위한 table 입니다 ex) 몇시,몇분에 어느좌석에 어떤 user가 몇시간을 사용했다~

    @Enumerated(EnumType.STRING)
    private SeatReservationInfoStateType isValid;

    //Entity User
    @Column(name = "USER_ID")
    private String userLoginId;
    //Entity Cafe
    @Column(name = "C_NAME")
    private String cafeName;
    //Entity Cafe + Seat
    @Column(name = "S_N")
    private Integer seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "T_ID") // 어떤 ticket을
    private Ticket ticket;

    @Column(name = "S_T")
    private Long startTime;

    private Long timeInUse; // 실제 사용한 시간, 분단위 ( 이용종료시 )

    @Builder
    public SeatReservationInfo(User user, Cafe cafe, Seat seat, Ticket ticket, Long startTime, Long timeInUse) {
        this.userLoginId = user.getLoginId();
        this.cafeName = cafe.getCafeName();
        this.seatNumber = seat.getSeatNumber();
        this.ticket = ticket;
        this.startTime = startTime;
        //자동 값 입력
        this.timeInUse = timeInUse;
        this.isValid = SeatReservationInfoStateType.VALID;
    }

    public Long updateTimeInUse() {
        this.timeInUse = Duration.between(getCreateDate(), LocalDateTime.now()).toMinutes();
        return timeInUse;
    }

    public Long endUsingSeat() {
        this.isValid = SeatReservationInfoStateType.INVALID;
        this.timeInUse = Duration.between(getCreateDate(), LocalDateTime.now()).toMinutes();
        return timeInUse;
    }
}
