package asc.portfolio.ascSb.web.dto.room;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomSelectResponseDto {

    private Integer seatNumber;
    private String seatState;

    public RoomSelectResponseDto(RoomSelectResponseDto entity) {
        this.seatNumber = entity.getSeatNumber();
        this.seatState = entity.getSeatState();
    }
}