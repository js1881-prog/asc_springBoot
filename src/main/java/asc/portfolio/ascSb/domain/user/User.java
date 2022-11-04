package asc.portfolio.ascSb.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Entity
public class User {

  //TODO 필수값 처리는 어떻게?
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  private String loginId;
  private String password;
  private String email;

  private String name;
  private String nickname;
  
  //qrCode : id를 (?) 연산하여 qrCode 생성
//  private String qrCode;

  @Builder
  public User(String loginId, String password, String email, String name, String nickname) {
    this.loginId = loginId;
    this.password = password;
    this.email = email;
    this.name = name;
    this.nickname = nickname;
  }
}
