package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.service.seat.SeatService;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/api/v1/seat")
    public List<SeatSelectResponseDto> seatState() {
        return seatService.showAllSeat();
    }
}
