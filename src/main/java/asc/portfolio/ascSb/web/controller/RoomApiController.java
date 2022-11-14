package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.service.room.RoomService;
import asc.portfolio.ascSb.web.dto.room.RoomListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class RoomApiController {

    private final RoomService roomService;

    @GetMapping("/api/v1/room")
    public List<RoomListResponseDto> roomState() {
        return roomService.showAllRoom();
    }
}
