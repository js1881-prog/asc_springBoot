package asc.portfolio.ascSb.scheduler;

import asc.portfolio.ascSb.domain.seat.SeatRepository;
import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.service.seat.SeatService;
import asc.portfolio.ascSb.service.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TableUpdateScheduler {

    private final SeatService seatService;

    private final TicketService ticketService;

    @Scheduled(fixedDelay = 1000 * 60)
    public void updateSeatAndTicketState() {
        log.debug("update All Seat State");
        //현재 사용중인 전체 좌석에 대해 상태 업데이트를 진행한다. (seat, seatReservationInfo, ticket)
        seatService.updateAllSeatState();

        log.debug("update All Ticket State");
        //Fixed Ticket 상태 업데이트를 진행한다.
        ticketService.updateAllTicketState();
    }
}
