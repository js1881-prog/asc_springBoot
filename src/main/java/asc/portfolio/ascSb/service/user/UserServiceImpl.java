package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.jwt.AuthenticationContext;
import asc.portfolio.ascSb.jwt.JwtTokenProvider;
import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public Long signUp(UserSignupDto signUpDto) {

    User user = signUpDto.toEntity();

    User saveUser = userRepository.save(user);

    return saveUser.getId();
  }

  @Override
  public User checkPassword(String loginId, String password) {
    return userRepository.findByLoginId(loginId)
            .filter(m -> m.getPassword().equals(password))
            .orElse(null);
  }

  @Override
  public User checkJsonWebToken(String jwt) {

    if ((jwt == null) || jwt.isBlank()) {
      return null;
    }

    try {
      String loginId = jwtTokenProvider.extractSubject(jwt);
      return userRepository.findByLoginId(loginId).orElse(null);
    } catch (IllegalStateException e) {
      log.error(e.toString());
      return null;
    }
  }

  @Override
  public List<UserQrAndNameResponseDto> userQrAndName(Long id) {
    return userRepository.findQrAndUserNameById(id)
            .stream()
            .map(UserQrAndNameResponseDto::new)
            .collect(Collectors.toList());
  }
}
