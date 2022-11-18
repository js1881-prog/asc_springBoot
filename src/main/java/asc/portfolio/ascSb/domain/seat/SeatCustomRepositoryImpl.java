package asc.portfolio.ascSb.domain.seat;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static asc.portfolio.ascSb.domain.seat.QSeat.*;

@RequiredArgsConstructor
@Repository
public class SeatCustomRepositoryImpl implements SeatCustomRepository {

    private final JPAQueryFactory query;

    public List<SeatSelectResponseDto> findSeatNumberAndSeatState() {
        return query
                .select(Projections.bean(SeatSelectResponseDto.class, seat.seatNumber, seat.seatState))
                .from(seat)
                .orderBy(seat.seatNumber.asc())
                .fetch();
    }
}
