package asc.portfolio.ascSb.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @AfterEach
  public void cleanup() {
    userRepository.deleteAll();
  }

  @Test
  public void user_저장및불러오기() {

    //given
    String password = "ascUser1234";
    String email = "asc@gmail.com";
    String name = "asc";
    String nickname = "asc";
    String qrCode = "qrcode";

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
}