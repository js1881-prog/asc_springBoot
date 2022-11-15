package asc.portfolio.ascSb.web.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketSelectResponseDto {

    private String isDeprecatedTicket;
    private LocalDateTime fixedTermTicket; // 기간제 티켓 날짜 => fixedTermTicket - createDate 시간으로 남은기간 계산
    private Integer partTimeTicket; // 결제한 시간제 티켓시간 // 50시간, 100시간
    private Integer remainingTime; // 시간제 티켓 남은시간

    public TicketSelectResponseDto(TicketSelectResponseDto entity) {
        this.isDeprecatedTicket = entity.getIsDeprecatedTicket();
        this.fixedTermTicket = entity.getFixedTermTicket();
        this.partTimeTicket = entity.getPartTimeTicket();
        this.remainingTime = entity.getRemainingTime();
    }
}
