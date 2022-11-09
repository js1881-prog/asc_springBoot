package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

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
}
