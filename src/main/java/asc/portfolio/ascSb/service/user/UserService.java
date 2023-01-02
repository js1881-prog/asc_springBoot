package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.user.UserForAdminResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;

public interface UserService {

  Long signUp(UserSignupDto signUpDto) throws Exception;

  User checkPassword(String loginId, String password) throws Exception;

  User checkAccessToken(String jwt);

  UserQrAndNameResponseDto userQrAndName(Long id);

  UserForAdminResponseDto AdminCheckUserInfo(String userId);

}
