package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.seat.SeatService;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/seat")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{cafeName}")
    public List<SeatSelectResponseDto> seatState(@PathVariable String cafeName) {
        return seatService.showCurrentSeatState(cafeName);
    }

    @GetMapping("/reservation/{seatNumber}")
    public ResponseEntity<String> reserveSeat(@LoginUser User user, @PathVariable int seatNumber) {

        //선택 된 카페가 없음.
        if (user.getCafe() == null) {
            return new ResponseEntity<>("Select a cafe first", HttpStatus.BAD_REQUEST);
        }

        Boolean isSuccess = seatService.reserveSeat(user, user.getCafe(), seatNumber);
        if (!isSuccess) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/exit")
    public ResponseEntity<String> exitSeat(@LoginUser User user) {

        Boolean isSuccess = seatService.exitSeat(user);
        if (!isSuccess) {
            return new ResponseEntity<>("No seat where the user sat", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
