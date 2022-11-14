package asc.portfolio.ascSb.web.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserLoginDto {

  @NotBlank
  @Length(min = 8, max = 16)
  private String loginId;

  @NotBlank
  @Length(min = 8, max = 16)
  private String password;

  @Builder
  public UserLoginDto(String loginId, String password) {
    this.loginId = loginId;
    this.password = password;
  }
}
