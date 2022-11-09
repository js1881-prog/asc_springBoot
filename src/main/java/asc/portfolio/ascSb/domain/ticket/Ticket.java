package asc.portfolio.ascSb.domain.ticket;
import asc.portfolio.ascSb.domain.BaseTimeEntity;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TICKET")
public class Ticket extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private String isDeprecatedTicket;

    @Column(name = "T_P")
    private int ticketPrice;

    @Column(name = "FT_T")
    private Date fixedTermTicket; // 기간제 티켓 날짜 => fixedTermTicket - createDate 시간으로 남은기간 계산

    @Column(name = "PT_T") // 결제한 시간제 티켓시간
    private int partTimeTicket; // 50시간, 100시간

    @Column(name = "R_T") // 시간제 티켓 남은시간
    private int remainingTime;

}
