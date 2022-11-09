package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.web.dto.user.UserLoginDto;
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
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    User findUser = userRepository.findByLoginId(loginId).orElseThrow();

    assertThat(findUser.getId()).isGreaterThan(0L);
    assertThat(findUser.getPassword()).isEqualTo(password);
    assertThat(findUser.getEmail()).isEqualTo(email);
  }

  @Test
  public void User_중복등록() {
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
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);
    ResponseEntity<String> duplicateEntity = restTemplate.postForEntity(url, requestDto, String.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(duplicateEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  public void User_패스워드_UppercaseStart() {
    //given
    String loginId = "ascUser1";
    String password = "Test1234";
    String email = "email@gmail.com";

    UserSignupDto requestDto = UserSignupDto.builder()
            .loginId(loginId)
            .password(password)
            .email(email)
            .build();

    String url = "http://localhost:" + port + "/user/signup";

    //when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    User findUser = userRepository.findByLoginId(loginId).orElseThrow();

    assertThat(findUser.getPassword()).isEqualTo(password);
  }

  @Test
  public void User_패스워드_LowercaseStart() {
    //given
    String loginId = "ascUser1";
    String password = "test1234";
    String email = "email@gmail.com";

    UserSignupDto requestDto = UserSignupDto.builder()
            .loginId(loginId)
            .password(password)
            .email(email)
            .build();

    String url = "http://localhost:" + port + "/user/signup";

    //when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    User findUser = userRepository.findByLoginId(loginId).orElseThrow();

    assertThat(findUser.getPassword()).isEqualTo(password);
  }

  @Test
  public void User_Login() {
    //given
    String loginId = "ascUser1";
    String password = "test1234";
    String email = "email@gmail.com";

    UserSignupDto requestSignupDto = UserSignupDto.builder()
            .loginId(loginId)
            .password(password)
            .email(email)
            .build();

    UserLoginDto requestLoginDto = UserLoginDto.builder()
            .loginId(loginId)
            .password(password)
            .build();

    String urlSignup = "http://localhost:" + port + "/user/signup";
    String urlLogin = "http://localhost:" + port + "/user/login";

    //when
    //선 회원가입
    ResponseEntity<String> respSignupEntity = restTemplate.postForEntity(urlSignup, requestSignupDto, String.class);
    //후 토큰 수령
    ResponseEntity<String> respLoginEntity = restTemplate.postForEntity(urlLogin, requestLoginDto, String.class);

    //then
    //Http Status 확인
    assertThat(respSignupEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(respLoginEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    //TODO 토큰 인가 여부 확인
  }
}
