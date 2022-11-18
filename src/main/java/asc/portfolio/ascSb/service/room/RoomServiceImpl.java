package asc.portfolio.ascSb.service.room;
import asc.portfolio.ascSb.domain.room.Room;
import asc.portfolio.ascSb.domain.room.RoomRepository;
import asc.portfolio.ascSb.domain.seat.SeatRepository;
import asc.portfolio.ascSb.web.dto.room.RoomListResponseDto;
import asc.portfolio.ascSb.web.dto.room.RoomResponseDto;
import asc.portfolio.ascSb.web.dto.room.RoomSelectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;

    @Override
    public List<RoomSelectResponseDto> showAllRoom() {
        return roomRepository.findSeatNumberAndSeatState().stream()
                .map(RoomSelectResponseDto::new)
                .collect(Collectors.toList());
    }
}
