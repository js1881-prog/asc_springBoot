package asc.portfolio.ascSb.service.jwt;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.jwt.JwtTokenProvider;
import asc.portfolio.ascSb.service.user.UserService;
import asc.portfolio.ascSb.web.dto.user.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserService userService;

  public UserLoginResponseDto createToken(String loginId, String password) throws Exception {
    User user = userService.checkPassword(loginId, password);

    if (user == null) {
      return null;
    }

    String token = jwtTokenProvider.createToken(user.getLoginId());

    return new UserLoginResponseDto(user.getRole(), token);
  }
}
