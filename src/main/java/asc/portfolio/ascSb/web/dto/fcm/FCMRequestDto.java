package asc.portfolio.ascSb.web.dto.fcm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class FCMRequestDto {
    private String date;
    private String type;
    private String target;
    private String specific_user;
    private String title;
    private String body;
}
