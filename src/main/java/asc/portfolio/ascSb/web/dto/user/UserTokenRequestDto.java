package asc.portfolio.ascSb.web.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTokenRequestDto {
    private String accessToken;
    private String refreshToken;
}
