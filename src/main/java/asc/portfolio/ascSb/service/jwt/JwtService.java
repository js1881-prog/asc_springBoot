package asc.portfolio.ascSb.service.jwt;

import asc.portfolio.ascSb.domain.redisrepo.RedisRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.jwt.JwtTokenProvider;
import asc.portfolio.ascSb.service.user.UserService;
import asc.portfolio.ascSb.web.dto.user.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

  private final JwtTokenProvider jwtTokenProvider;

  private final UserService userService;

  private final RedisRepository redisRepository;

  public UserLoginResponseDto createToken(String loginId, String password) throws Exception {

    // user 조회
    User user = userService.checkPassword(loginId, password);

    if (user == null) {
      return null;
    }

    // token 생성
    String accessToken = jwtTokenProvider.createAccessToken(user.getLoginId());
    String refreshToken = jwtTokenProvider.createRefreshToken();

    log.info("accessToken={}", accessToken);
    log.info("refreshToken={}", refreshToken);

    // refreshToken 저장 (redis)
    redisRepository.saveValue(loginId, refreshToken, jwtTokenProvider.getRefreshTime());

    return new UserLoginResponseDto(user.getRole(), accessToken, refreshToken);
  }

  public String getRefreshToken(String loginId) {
    return redisRepository.getValue(loginId);
  }
}
