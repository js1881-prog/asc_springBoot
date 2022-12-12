package asc.portfolio.ascSb.web.dto.seat;


import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.seat.SeatStateType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SeatSelectResponseDto {

    private Integer seatNumber;
    private SeatStateType seatState;

    public SeatSelectResponseDto(SeatSelectResponseDto entity) {
        this.seatNumber = entity.getSeatNumber();
        this.seatState = entity.getSeatState();
    }
}