package asc.portfolio.ascSb.domain.room;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static asc.portfolio.ascSb.domain.room.QRoom.room;

@RequiredArgsConstructor
@Repository
public class RoomCustomRepositoryImpl implements RoomCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Room> show() {
        return jpaQueryFactory
                .selectFrom(room)
                .fetch();
    }
}
