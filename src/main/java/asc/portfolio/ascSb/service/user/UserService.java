package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.web.dto.user.SignUpDto;

public interface UserService {

  public Long signUp(SignUpDto signUpDto);
}
