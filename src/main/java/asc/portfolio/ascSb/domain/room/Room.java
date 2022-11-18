package asc.portfolio.ascSb.domain.room;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_ID", nullable = false)
    private Long id; // 별개 PK일뿐이에요

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_ID")
    private Cafe cafe; // CAFE FK

    @Column(name = "SN", nullable = false) // 1~40번
    private int seatNumber;

    // 좌석 상태
    @Enumerated(EnumType.STRING)
    private SeatStateType seatState;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "T_ID")
    private Ticket ticket;

    @Builder
    public Room(int seatNumber, Cafe cafe) {
        this.cafe = cafe;
        this.seatNumber = seatNumber;
        this.seatState = SeatStateType.UNRESERVED;
    }

    public void reserveRoom(User user) {
        this.user = user;
        this.seatState = SeatStateType.RESERVED;
        //this.ticket
    }

    public void exitRoom(User user) {
        this.user = null;
        this.seatState = SeatStateType.UNRESERVED;
    }
}
