package asc.portfolio.ascSb.domain.seatreservationinfo;

import java.util.List;

public interface SeatReservationInfoCustomRepository {

  public SeatReservationInfo findUserValidTypeReservationInfo(String loginId);
}
