package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.ticket.TicketService;
import asc.portfolio.ascSb.web.dto.room.RoomSelectResponseDto;
import asc.portfolio.ascSb.web.dto.ticket.TicketSelectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/api/v1/ticket")
    public List<TicketSelectResponseDto> userTicket(@LoginUser User user) {
        return ticketService.userTicket(user.getId());
    }
}
