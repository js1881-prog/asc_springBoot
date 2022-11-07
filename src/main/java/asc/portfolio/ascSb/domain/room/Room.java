package asc.portfolio.ascSb.domain.room;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.room.converter.BooleanToStringConverter;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    // update 쿼리를 계속날려서 계정 업데이트 되는걸로

    @Convert(converter= BooleanToStringConverter.class) // Y,N 상태로 둘 중 하나의 상태로 저장
    private Boolean seatState; // 비어있으면 N , 사용중이면 Y

    @OneToOne
    @JoinColumn(name = "L_ID")
    private User loginId;

    @OneToOne
    @JoinColumn(name = "T_ID")
    private Ticket ticketId;

    @Builder
    public Room(Long id, int seatNumber, Cafe cafe, Boolean seatState, User loginId, Ticket ticketId) {
        this.id = id;
        this.cafeId = cafe;
        this.seatNumber = seatNumber;
        this.seatState = seatState;
        this.loginId = loginId;
        this.ticketId = ticketId;
    }
}
