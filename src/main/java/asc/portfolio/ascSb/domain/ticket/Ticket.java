package asc.portfolio.ascSb.domain.ticket;
import asc.portfolio.ascSb.domain.BaseTimeEntity;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TICKET")
public class Ticket extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "C_ID")
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="USER_ID")
    private User user;

    @Enumerated(EnumType.STRING)
    private TicketStateType isValidTicket;

    @Column(name = "T_P")
    private Integer ticketPrice;

    @Column(name = "FT_T")
    private LocalDateTime fixedTermTicket; // 기간제 티켓 날짜 => fixedTermTicket - createDate 시간으로 남은기간 계산

    @Column(name = "PT_T") // 결제한 시간제 티켓시간
    private Integer partTimeTicket; // 50시간, 100시간

    @Column(name = "R_T") // 시간제 티켓 남은시간
    private Integer remainingTime;

    @Column(unique = true)
    private String productLabel;

    @Builder
    public Ticket(Cafe cafe, User user, TicketStateType isValidTicket, Integer ticketPrice, LocalDateTime fixedTermTicket, Integer partTimeTicket, Integer remainingTime) {
        this.cafe = cafe;
        this.user = user;
        this.isValidTicket = isValidTicket;
        this.ticketPrice = ticketPrice;
        this.fixedTermTicket = fixedTermTicket;
        this.partTimeTicket = partTimeTicket;
        this.remainingTime = remainingTime;
    }

    public void changeTicketStateToInvalid() {
        this.isValidTicket = TicketStateType.INVALID;
    }
}
