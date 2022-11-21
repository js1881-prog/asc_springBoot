package asc.portfolio.ascSb.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  public void user_저장및불러오기() {

    //given
    String loginId = "ascUser1234";
    String password = "ascUser1234";
    String email = "asc@gmail.com";
    String name = "asc";

    //when
    User user = User.builder()
            .loginId(loginId)
            .password(password)
            .email(email)
            .name(name)
            .role(UserRoleType.USER)
            .build();

    userRepository.save(user);
    List<User> userList = userRepository.findAll();

    //then
    User findUser = userList.get(0);
    assertThat(findUser.getPassword()).isEqualTo(password);
    assertThat(findUser.getEmail()).isEqualTo(email);
    assertThat(findUser.getName()).isEqualTo(name);
  }
}