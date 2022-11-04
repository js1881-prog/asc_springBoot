package asc.portfolio.ascSb.web.dto.user;

import asc.portfolio.ascSb.domain.user.User;
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

  //TODO 추후 기능 추가 (name, nickname)
  private String name;
  private String nickname;

  @Builder
  public UserSignupDto(String loginId, String password, String email, String name, String nickname) {
    this.loginId = loginId;
    this.password = password;
    this.email = email;
    this.name = name;
    this.nickname = nickname;
  }

  public User toEntity() {
    return User.builder()
            .loginId(loginId)
            .password(password)
            .email(email)
            .name(name)
            .nickname(nickname)
            .build();
  }
}
