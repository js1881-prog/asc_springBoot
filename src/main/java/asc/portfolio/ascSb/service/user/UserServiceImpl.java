package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Long signUp(UserSignupDto signUpDto) {

    //TODO 작성중...
    User user = signUpDto.toEntity();

    User saveUser = userRepository.save(user);

    return saveUser.getId();
  }

  @Override
  public List<UserQrAndNameResponseDto> userQrAndName(Long id) {
    return userRepository.findQrAndUserNameById(id)
            .stream()
            .map(UserQrAndNameResponseDto::new)
            .collect(Collectors.toList());
  }
}
