package asc.portfolio.ascSb.service.seatreservationinfo;

import asc.portfolio.ascSb.web.dto.seatReservationInfo.SeatReservationInfoSelectResponseDto;

public interface SeatReservationInfoService {

    public SeatReservationInfoSelectResponseDto showUserSeatReservationInfo(String loginId, String cafeName);

}
