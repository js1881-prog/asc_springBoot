package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;

public interface UserService {

  public Long signUp(UserSignupDto signUpDto);

  public User checkPassword(String loginId, String password);
}
