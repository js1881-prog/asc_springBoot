package asc.portfolio.ascSb.service.room;
import asc.portfolio.ascSb.domain.room.Room;
import asc.portfolio.ascSb.domain.room.RoomRepository;
import asc.portfolio.ascSb.web.dto.room.RoomListResponseDto;
import asc.portfolio.ascSb.web.dto.room.RoomResponseDto;
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

    @Override
    public List<RoomListResponseDto> showAllRoom() {
        return roomRepository.show().stream()
                .map(RoomListResponseDto::new)
                .collect(Collectors.toList());
    }
}
