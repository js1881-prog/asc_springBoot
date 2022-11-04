package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.web.dto.user.UserSignupDto;

public interface UserService {

  public Long signUp(UserSignupDto signUpDto);
}
