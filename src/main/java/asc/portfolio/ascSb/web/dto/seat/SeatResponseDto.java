package asc.portfolio.ascSb.web.dto.seat;
import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.seat.SeatStateType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SeatResponseDto {

    //From Seat
    private Integer seatNumber;
    private SeatStateType seatState;

    //Form SeatReservationInfo
    private Long startTime;

    //From Ticket
    private LocalDateTime fixedTermTicket;
    private Long partTimeTicket;
    private Long remainingTime;

    @Builder
    private SeatResponseDto(Integer seatNumber, SeatStateType seatState, Long startTime, LocalDateTime fixedTermTicket, Long partTimeTicket, Long remainingTime) {
        this.seatNumber = seatNumber;
        this.seatState = seatState;
        this.startTime = startTime;
        this.fixedTermTicket = fixedTermTicket;
        this.partTimeTicket = partTimeTicket;
        this.remainingTime = remainingTime;
    }

    //정적 팩토리 매세드
    public static SeatResponseDto setUnReservedSeat(Integer seatNumber, SeatStateType seatState) {
        return SeatResponseDto.builder()
                .seatNumber(seatNumber)
                .seatState(seatState)
                .build();
    }

    //정적 팩토리 메서드
    public static SeatResponseDto setFixedTermSeat(Integer seatNumber, SeatStateType seatState, Long startTime, LocalDateTime fixedTermTicket) {
        return SeatResponseDto.builder()
                .seatNumber(seatNumber)
                .seatState(seatState)
                .startTime(startTime)
                .fixedTermTicket(fixedTermTicket)
                .build();
    }

    //정적 팩토리 메서드
    public static SeatResponseDto setPartTimeSeat(Integer seatNumber, SeatStateType seatState, Long startTime, Long partTimeTicket, Long remainingTime) {
        return SeatResponseDto.builder()
                .seatNumber(seatNumber)
                .seatState(seatState)
                .startTime(startTime)
                .partTimeTicket(partTimeTicket)
                .remainingTime(remainingTime)
                .build();
    }
}
