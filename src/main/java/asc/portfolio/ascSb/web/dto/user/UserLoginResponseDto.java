package asc.portfolio.ascSb.web.dto.user;

import asc.portfolio.ascSb.domain.user.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLoginResponseDto {

  UserRoleType roleType;
  String accessToken;
  String refreshToken;
}
