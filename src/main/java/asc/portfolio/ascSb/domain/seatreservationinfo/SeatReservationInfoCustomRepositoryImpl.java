package asc.portfolio.ascSb.domain.seatreservationinfo;

import asc.portfolio.ascSb.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static asc.portfolio.ascSb.domain.seatreservationinfo.QSeatReservationInfo.*;

@Slf4j
@RequiredArgsConstructor
@Repository
public class SeatReservationInfoCustomRepositoryImpl implements SeatReservationInfoCustomRepository {

  private final JPAQueryFactory query;

  @Override
  public SeatReservationInfo findUserValidTypeReservationInfo(String loginId) {

    List<SeatReservationInfo> result = query
            .select(seatReservationInfo)
            .from(seatReservationInfo)
            .where(seatReservationInfo.userLoginId.eq(loginId),
                    seatReservationInfo.isValid.eq(SeatReservationInfoType.VALID))
            .orderBy(seatReservationInfo.startTime.desc())
            .fetch();

    int listSize = result.size();
    if (listSize == 0) {
      // 정보 없음
      log.error("No valid reservation. user = {}", loginId);
      return null;
    } else if (listSize > 1) {
      // Valid 상태의 reservation info 는 한개여야 한다.
      log.error("The user{}'s valid state information exceed one.", loginId);
    }

    return result.get(0);
  }
}
