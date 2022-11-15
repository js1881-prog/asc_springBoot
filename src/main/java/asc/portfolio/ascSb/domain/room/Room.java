package asc.portfolio.ascSb.domain.room;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter // test를 위한 setter 나중에 제거
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_ID", nullable = false)
    private Long id; // 별개 PK일뿐이에요

    @ManyToOne
    @JoinColumn(name = "C_ID")
    private Cafe cafeId; // CAFE FK

    @Column(name = "SN") // 1~40번
    private int seatNumber;

    // 좌석 상태
    private String seatState;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User loginId;

    @OneToOne
    @JoinColumn(name = "T_ID")
    private Ticket ticketId;

    @Builder
    public Room(Long id, int seatNumber, Cafe cafe, String seatState, User loginId, Ticket ticketId) {
        this.id = id;
        this.cafeId = cafe;
        this.seatNumber = seatNumber;
        this.seatState = seatState;
        this.loginId = loginId;
        this.ticketId = ticketId;
    }
}
