package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;

import java.util.List;

public interface UserService {

  Long signUp(UserSignupDto signUpDto);

  List<UserQrAndNameResponseDto> userQrAndName(Long id);

}
