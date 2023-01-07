package asc.portfolio.ascSb.service.user;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.user.*;

public interface UserService {

  Long signUp(UserSignupDto signUpDto) throws Exception;

  UserLoginResponseDto checkPassword(String loginId, String password);

  User checkAccessToken(String jwt);

  UserLoginResponseDto reissueToken(UserTokenRequestDto tokenRequestDto);

  UserQrAndNameResponseDto userQrAndName(Long id);

  UserForAdminResponseDto AdminCheckUserInfo(String userId);

  boolean checkLoginId(String userLoginId);
}
