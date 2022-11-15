package asc.portfolio.ascSb.domain.room;
import asc.portfolio.ascSb.web.dto.room.RoomSelectResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static asc.portfolio.ascSb.domain.room.QRoom.room;

@RequiredArgsConstructor
@Repository
public class RoomCustomRepositoryImpl implements RoomCustomRepository {

    private final JPAQueryFactory query;

    public List<RoomSelectResponseDto> findSeatNumberAndSeatState() {
        return query
                .select(Projections.bean(RoomSelectResponseDto.class, room.seatNumber, room.seatState))
                .from(room)
                .orderBy(room.seatNumber.asc())
                .fetch();
    }
}
