package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.ticket.TicketService;
import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ticket")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{cafeName}")
    public TicketResponseDto userTicket(@LoginUser User user, @PathVariable String cafeName) {
        return ticketService.userTicket(user.getId(), cafeName);
    }

    @GetMapping("/lookup")
    public ResponseEntity<List<TicketResponseDto>> lookupUserTickets(@LoginUser User admin, @RequestParam("user") String targetUserLoginId) {
        if (admin.getRole() == UserRoleType.ADMIN) {
            if (admin.getCafe() != null) {
                log.info("lookup tickets. user = {}", targetUserLoginId);
                List<TicketResponseDto> ticketResponseDtos = ticketService.lookupUserTickets(targetUserLoginId, admin.getCafe());
                return new ResponseEntity<>(ticketResponseDtos, HttpStatus.OK);
            } else {
                log.error("Set up a cafe field first");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

        } else {
            log.info("Unauthorized User");
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
