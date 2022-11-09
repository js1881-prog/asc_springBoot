package asc.portfolio.ascSb.service.jwt;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.jwt.JwtTokenProvider;
import asc.portfolio.ascSb.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserService userService;

  public String createToken(String loginId, String password) {
    User user = userService.checkPassword(loginId, password);

    //TODO null 들어오면 어떻게 되는지 확인!
    return jwtTokenProvider.createToken(user.getLoginId());
  }
}
