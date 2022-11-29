package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.seat.SeatRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.web.dto.user.UserLoginRequestDto;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

  @LocalServerPort
  public int port;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SeatRepository seatRepository;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @AfterEach
  public void clearRepository2() {
    //참조 무결설 제약 위반 Exception 해결을 위해 Seat DB도 초기화
    seatRepository.deleteAll();
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

    UserLoginRequestDto requestLoginDto = UserLoginRequestDto.builder()
            .loginId(loginId)
            .password(password)
            .build();

    String urlSignup = "http://localhost:" + port + "/user/signup";
    String urlLogin = "http://localhost:" + port + "/user/login";
    String urlLoginCheck = "http://localhost:" + port + "/user/login-check";

    //when
    //선 회원가입
    ResponseEntity<String> respSignupEntity = restTemplate.postForEntity(urlSignup, requestSignupDto, String.class);
    //후 토큰 수령
    ResponseEntity<String> respLoginEntity = restTemplate.postForEntity(urlLogin, requestLoginDto, String.class);

    //토큰 수령 후 jwt 인증 시도
    Map<String, String> jsonToMap;
    try {
      jsonToMap = objectMapper.readValue(respLoginEntity.getBody(), new TypeReference<Map<String, String>>() {});
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    String accessToken = jsonToMap.get("accessToken");
    HttpHeaders headers = new HttpHeaders();

    headers.add("Authorization", accessToken);
    ResponseEntity<String> respLoginCheck = restTemplate.postForEntity(urlLoginCheck, new HttpEntity<>(headers), String.class);

    //then
    //Http Status 확인
    assertThat(respSignupEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(respLoginEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(respLoginCheck.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
