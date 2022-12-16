package asc.portfolio.ascSb.domain.seatreservationinfo;

import asc.portfolio.ascSb.domain.ticket.TicketStateType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SeatReservationInfoRepository extends JpaRepository<SeatReservationInfo, Long>, SeatReservationInfoCustomRepository {

    SeatReservationInfo findByUserLoginIdAndIsValidAndCafeName(String userLoginId, SeatReservationInfoStateType isValid, String cafeName);

}