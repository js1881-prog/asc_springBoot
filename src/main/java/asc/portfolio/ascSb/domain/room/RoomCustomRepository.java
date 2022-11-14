package asc.portfolio.ascSb.domain.room;

import asc.portfolio.ascSb.web.dto.room.RoomSelectResponseDto;

import java.util.List;

public interface RoomCustomRepository {
    List<RoomSelectResponseDto> show();
}
