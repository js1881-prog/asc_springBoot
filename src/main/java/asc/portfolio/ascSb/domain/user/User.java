package asc.portfolio.ascSb.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER_TABLE") // user =  SQL 예약어로 에러 발생 => table_name만 교체했습니다 참고: https://onedaythreecoding.tistory.com/entry/ERROR-JPA-%EC%9E%90%EB%8F%99-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%83%9D%EC%84%B1-create-drop-%EC%8B%9C-GenerationTarget-encountered-exception-accepting-command-Error-executing-DDL
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
