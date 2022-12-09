package asc.portfolio.ascSb.domain.seat;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static asc.portfolio.ascSb.domain.cafe.QCafe.*;
import static asc.portfolio.ascSb.domain.seat.QSeat.*;

@RequiredArgsConstructor
@Repository
public class SeatCustomRepositoryImpl implements SeatCustomRepository {

    private final JPAQueryFactory query;

    public List<SeatSelectResponseDto> findSeatNumberAndSeatState(String cafeName) {
        return query
                .select(Projections.bean(SeatSelectResponseDto.class, seat.seatNumber, seat.seatState))
                .from(seat)
                .where(seat.cafe.cafeName.eq(cafeName))
                .orderBy(seat.seatNumber.asc())
                .fetch();
    }

    @Override
    public Seat findByCafeAndSeatNumber(Cafe cafeObject, int seatNumber) {
        return query
                .select(seat)
                .from(seat)
                .join(seat.cafe, cafe)
                .where(cafe.eq(cafeObject), seat.seatNumber.eq(seatNumber))
                .fetchOne();
    }
}
