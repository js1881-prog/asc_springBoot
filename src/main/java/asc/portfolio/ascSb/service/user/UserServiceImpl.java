package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.jwt.JwtTokenProvider;
import asc.portfolio.ascSb.loginutil.LoginUtil;
import asc.portfolio.ascSb.web.dto.user.UserForAdminResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final LoginUtil loginUtil;

  @Override
  public Long signUp(UserSignupDto signUpDto) {

    try {
      // id와 pw를 이용한 암호화
      signUpDto.setPassword(loginUtil.encryptPassword(signUpDto.getLoginId(), signUpDto.getPassword()));
      return userRepository.save(signUpDto.toEntity()).getId();
    } catch (Exception e) {
      log.error("비밀번호 암호화 실패");
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public User checkPassword(String loginId, String password) {
    Optional<User> invalidUser = userRepository.findByLoginId(loginId);
    if (invalidUser.isPresent()) {
      User validUser = invalidUser.get();
      // id, pw를 통해 암호화된 pw를 확인
      if (Objects.equals(validUser.getPassword(), loginUtil.encryptPassword(loginId, password))) {
        return validUser;
      }
    }

    log.error("비밀번호 혹은 아이디가 일치 하지 않습니다.");
    return null;
  }

  @Override
  public User checkJsonWebToken(String jwt) {
    if ((jwt == null) || jwt.isBlank()) {
      return null;
    }

    String[] jwtSplit = jwt.split(" ");
    if ((jwtSplit.length == 2) && (jwtSplit[0].equals("Bearer")) ) {
      jwt = jwtSplit[1];
    }

    try {
      log.info("jwt = {}", jwt);
      String loginId = jwtTokenProvider.extractSubject(jwt);
      return userRepository.findByLoginId(loginId).orElse(null);
    } catch (IllegalStateException e) {
      log.error(e.toString());
      return null;
    }
  }

  @Override
  public UserQrAndNameResponseDto userQrAndName(Long id) {
    return userRepository.findQrAndUserNameById(id);
  }

  @Override
  public UserForAdminResponseDto AdminCheckUserInfo(String userLoginId) {
    Optional<User> user = userRepository.findByLoginId(userLoginId);
    try {
      if (user.isPresent()) {
        User userInfo = user.get();
        return new UserForAdminResponseDto(userInfo);
      }
    } catch (Exception e) {
      log.error("해당하는 유저가 없습니다");
    }
    return null;
  }
}
