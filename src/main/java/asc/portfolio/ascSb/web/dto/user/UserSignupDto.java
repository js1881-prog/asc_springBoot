package asc.portfolio.ascSb.web.dto.user;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserSignupDto {

  @NotBlank
  @Length(min = 8, max = 16)
  private String loginId;

  //TODO Password 암호화 저장
  @NotBlank
  @Length(min = 8, max = 16)
  private String password;

  @Email
  private String email;

  private String name;

  @Builder
  public UserSignupDto(String loginId, String password, String email, String name) {
    this.loginId = loginId;
    this.password = password;
    this.email = email;
    this.name = name;
  }

  public User toEntity() {
    return User.builder()
            .loginId(loginId)
            .password(password)
            .email(email)
            .name(name)
            .role(UserRoleType.USER)
            .build();
  }
}
