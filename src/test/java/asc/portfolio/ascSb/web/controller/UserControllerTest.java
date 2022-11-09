package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

  @LocalServerPort
  public int port;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestRestTemplate restTemplate;

  @AfterEach
  public void clearAll() {
    userRepository.deleteAll();
  }

  @Test
  public void User_등록() {

    //given
    String loginId = "ascUser1";
    String password = "password";
    String email = "email@gmail.com";

    UserSignupDto requestDto = UserSignupDto.builder()
            .loginId(loginId)
            .password(password)
            .email(email)
            .build();

    String url = "http://localhost:" + port + "/user/signup";

    //when
    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    Long userId = responseEntity.getBody(); //생성 된 Id
    assertThat(userId).isGreaterThan(0L);

    //TODO: findByLoginId 로 바꾸기.
    User user = new User(); // User default 값
    Optional<User> userOptional = userRepository.findById(userId);
    User findUser = userOptional.orElse(user);

    assertThat(findUser.getLoginId()).isEqualTo(loginId);
  }
}
