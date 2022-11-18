package asc.portfolio.ascSb.service.room;
import asc.portfolio.ascSb.web.dto.room.RoomListResponseDto;
import asc.portfolio.ascSb.web.dto.room.RoomSelectResponseDto;

import java.util.List;

public interface RoomService {

    List<RoomSelectResponseDto> showAllRoom();
}
