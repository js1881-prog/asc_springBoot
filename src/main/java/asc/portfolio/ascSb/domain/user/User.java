package asc.portfolio.ascSb.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  private String password;
  private String email;

  private String name;
  private String nickname;

  private String qrCode;

  @Builder
  public User(String password, String email, String name, String nickname, String qrCode) {
    this.password = password;
    this.email = email;
    this.name = name;
    this.nickname = nickname;
    this.qrCode = qrCode;
  }
}
