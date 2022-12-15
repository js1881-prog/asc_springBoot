package asc.portfolio.ascSb.domain.seatreservationinfo;

import asc.portfolio.ascSb.web.dto.seatReservationInfo.SeatReservationInfoSelectResponseDto;

import java.util.List;

public interface SeatReservationInfoCustomRepository {

  public List<SeatReservationInfo> findValidSeatRezInfoByLoginId(String loginId);

  SeatReservationInfoSelectResponseDto findSeatInfoByUserIdAndCafeName(String loginId, String cafeName);

  public SeatReservationInfo findValidSeatRezInfoByCafeNameAndSeatNumber(String cafeName, Integer seatNumber);
}
