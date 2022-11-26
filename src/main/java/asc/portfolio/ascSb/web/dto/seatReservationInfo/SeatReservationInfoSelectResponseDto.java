package asc.portfolio.ascSb.web.dto.seatReservationInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SeatReservationInfoSelectResponseDto {
    private Integer seatNumber;
    private Integer startTime;
    private Integer timeInUse;

    private LocalDateTime createDate;

    private String period;

    public SeatReservationInfoSelectResponseDto(SeatReservationInfoSelectResponseDto entity) {
        this.seatNumber = entity.getSeatNumber();
        this.startTime = entity.getStartTime();
        this.timeInUse = entity.getTimeInUse();
        this.createDate = entity.getCreateDate();
        this.period = entity.getPeriod();
    }
}
