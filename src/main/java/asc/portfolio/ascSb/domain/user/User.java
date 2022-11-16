package asc.portfolio.ascSb.domain.user;

import asc.portfolio.ascSb.domain.BaseTimeEntity;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER_TABLE") // user =  SQL 예약어로 에러 발생 => user_table 로 교체했습니다 참고: https://onedaythreecoding.tistory.com/entry/ERROR-JPA-%EC%9E%90%EB%8F%99-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%83%9D%EC%84%B1-create-drop-%EC%8B%9C-GenerationTarget-encountered-exception-accepting-command-Error-executing-DDL
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long id;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Ticket> tickets = new ArrayList<>();

  @Size(min = 8, max = 16)
  @Column(name = "L_ID", unique = true)
  private String loginId;

  @Size(min = 8, max = 16)
  private String password;

  @Email
  @Column(unique = true)
  private String email;

  //TODO 추후 기능 추가
  private String name;
  private String nickname;
  private String qrCode;


  @Builder
  public User(String loginId, String password, String email, String name, String nickname) {
    this.loginId = loginId;
    this.password = password;
    this.email = email;
    this.name = name;
    this.nickname = nickname;
    this.qrCode = createQrString();
  }

  public String createQrString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    String generatedString = random.ints(leftLimit,rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

    return generatedString;
  }
}
