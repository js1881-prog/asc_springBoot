package asc.portfolio.ascSb.domain.user;

import asc.portfolio.ascSb.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER_TABLE") // user =  SQL 예약어로 에러 발생 => user_table 로 교체했습니다 참고: https://onedaythreecoding.tistory.com/entry/ERROR-JPA-%EC%9E%90%EB%8F%99-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%83%9D%EC%84%B1-create-drop-%EC%8B%9C-GenerationTarget-encountered-exception-accepting-command-Error-executing-DDL
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long id;

  @Size(min = 8, max = 16)
  @Column(unique = true)
  private String loginId;

  @Size(min = 8, max = 16)
  private String password;

  @Email
  @Column(unique = true)
  private String email;

  //TODO 추후 기능 추가
  private String name;
  private String nickname;
  
  @Builder
  public User(String loginId, String password, String email, String name, String nickname) {
    this.loginId = loginId;
    this.password = password;
    this.email = email;
    this.name = name;
    this.nickname = nickname;
  }
}
