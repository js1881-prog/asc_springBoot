package asc.portfolio.ascSb.service.seatreservationinfo;

import asc.portfolio.ascSb.domain.seatreservationinfo.SeatReservationInfo;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.seatReservationInfo.SeatReservationInfoSelectResponseDto;

public interface SeatReservationInfoService {

    public SeatReservationInfoSelectResponseDto showUserSeatReservationInfo(String loginId, String cafeName);

    SeatReservationInfo validUserSeatReservationInfo(User user);

}
