package asc.portfolio.ascSb.domain.seat;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;

import static asc.portfolio.ascSb.domain.cafe.QCafe.*;
import static asc.portfolio.ascSb.domain.seat.QSeat.*;

@Slf4j
@RequiredArgsConstructor
@Repository
public class SeatCustomRepositoryImpl implements SeatCustomRepository {

    private final JPAQueryFactory query;

    public void updateSeatState(String cafeName) {
        List<Seat> seatList = query
                .selectFrom(seat)
                .where(seat.cafe.cafeName.eq(cafeName))
                .orderBy(seat.seatNumber.asc())
                .fetch();

        for (Seat seat : seatList) {
            if (seat.getSeatState() == SeatStateType.RESERVED) {
                if (!seat.getTicket().isValidTicket()) {
                    log.info("Update SeatState SeatNumber={}", seat.getSeatNumber());
                    seat.setSeatStateTypeUnReserved();
                }
            }
        }
    }

    public List<SeatSelectResponseDto> findSeatNumberAndSeatStateList(String cafeName) {
        updateSeatState(cafeName);

        return query
                .select(Projections.bean(SeatSelectResponseDto.class, seat.seatNumber, seat.seatState))
                .from(seat)
                .where(seat.cafe.cafeName.eq(cafeName))
                .orderBy(seat.seatNumber.asc())
                .fetch();
    }

    @Override
    public Seat findByCafeAndSeatNumber(Cafe cafeObject, Integer seatNumber) {
        return query
                .select(seat)
                .from(seat)
                .join(seat.cafe, cafe)
                .where(cafe.eq(cafeObject), seat.seatNumber.eq(seatNumber))
                .fetchOne();
    }

    @Override
    public Seat findByCafeNameAndSeatNumber(String cafeName, int seatNumber) {
        return query
                .select(seat)
                .from(seat)
                .join(seat.cafe, cafe)
                .where(cafe.cafeName.eq(cafeName), seat.seatNumber.eq(seatNumber))
                .fetchOne();
    }
}
