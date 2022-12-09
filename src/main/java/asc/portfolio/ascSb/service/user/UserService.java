package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.user.UserForAdminResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;

public interface UserService {

  Long signUp(UserSignupDto signUpDto);

  User checkPassword(String loginId, String password);

  User checkJsonWebToken(String jwt);

  UserQrAndNameResponseDto userQrAndName(Long id);

  UserForAdminResponseDto AdminCheckUserInfo(String userId);

}
