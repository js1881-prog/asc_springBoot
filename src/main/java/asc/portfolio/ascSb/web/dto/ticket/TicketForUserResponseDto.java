package asc.portfolio.ascSb.web.dto.ticket;

import asc.portfolio.ascSb.domain.ticket.TicketStateType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketForUserResponseDto {

    private TicketStateType isValidTicket;
    private LocalDateTime fixedTermTicket; // 기간제 티켓 날짜 => fixedTermTicket - createDate 시간으로 남은기간 계산
    private Integer partTimeTicket; // 결제한 시간제 티켓시간 // 50시간, 100시간
    private Integer remainingTime; // 시간제 티켓 남은시간

    private long period;

}
