package asc.portfolio.ascSb.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

  //TODO 필수값 처리는 어떻게?
  @Id
  @Column(name = "user_id")
  private Long id;

  private String password;
  private String email;

  private String name;
  private String nickname;
  
  //qrCode : id를 (?) 연산하여 qrCode 생성
//  private String qrCode;

  @Builder
  public User(Long id, String password, String email, String name, String nickname) {
    this.id = id;
    this.password = password;
    this.email = email;
    this.name = name;
    this.nickname = nickname;
  }
}
