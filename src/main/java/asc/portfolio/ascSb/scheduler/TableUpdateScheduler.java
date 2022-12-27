package asc.portfolio.ascSb.scheduler;

import asc.portfolio.ascSb.service.seat.SeatService;
import asc.portfolio.ascSb.service.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!test") // test 프로필이 아닌 경우에만 활성화
@RequiredArgsConstructor
public class TableUpdateScheduler {

    private final SeatService seatService;

    private final TicketService ticketService;

    @Scheduled(fixedDelay = 1000 * 60)
    public void updateSeatAndTicketState() {

        //현재 사용중인 전체 좌석에 대해 상태 업데이트를 진행한다. (seat, seatReservationInfo, ticket)
        log.debug("update All Seat State");
        seatService.updateAllReservedSeatState();

        //Fixed Ticket 상태 업데이트를 진행한다.
        log.debug("update All Ticket State");
        Long updateCount = ticketService.updateAllValidTicketState();
        log.info("update Ticket. count={}", updateCount);
    }
}
