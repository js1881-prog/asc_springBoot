package asc.portfolio.ascSb.domain.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

//  @AfterEach
//  public void cleanup() {
//    userRepository.deleteAll();
//  }

  @Test
  public void user_저장및불러오기() {

    //given
    String password = "ascUser1234";
    String email = "asc@gmail.com";
    String name = "asc";
    String nickname = "asc";

    //when
    User user = User.builder()
            .password(password)
            .email(email)
            .name(name)
            .nickname(nickname)
            .build();

    userRepository.save(user);
    List<User> userList = userRepository.findAll();

    //then
    User findUser = userList.get(0);
    assertThat(findUser.getPassword()).isEqualTo(password);
    assertThat(findUser.getEmail()).isEqualTo(email);
    assertThat(findUser.getName()).isEqualTo(name);
    assertThat(findUser.getNickname()).isEqualTo(nickname);
  }

  @Test
  public void BaseTimeEntityRegistering() {


    String password = "1234";
    String email = "asc@gmail.com";
    String name = "asc";
    String nickname = "asc";

    //given
    LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);

    //when
    User userBuilder = User.builder()
            .password(password)
            .email(email)
            .name(name)
            .nickname(nickname)
            .build();

    userRepository.save(userBuilder);
    List<User> userList = userRepository.findAll();

    //then
    User user = userList.get(0);

    System.out.println(">>>>>>>>>>>> createDate="+user.getCreateDate()+", modifiedDate="+user.getModifiedDate());

    assertThat(user.getCreateDate()).isAfter(now);
  }
}