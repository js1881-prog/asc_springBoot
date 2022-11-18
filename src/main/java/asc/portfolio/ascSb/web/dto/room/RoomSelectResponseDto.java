package asc.portfolio.ascSb.web.dto.room;


import asc.portfolio.ascSb.domain.room.SeatStateType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomSelectResponseDto {

    private Integer seatNumber;
    private SeatStateType seatState;

    public RoomSelectResponseDto(RoomSelectResponseDto entity) {
        this.seatNumber = entity.getSeatNumber();
        this.seatState = entity.getSeatState();
    }
}