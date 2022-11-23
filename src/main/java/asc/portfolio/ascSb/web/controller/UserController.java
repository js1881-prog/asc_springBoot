package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.jwt.JwtService;
import asc.portfolio.ascSb.service.user.UserService;
import asc.portfolio.ascSb.web.dto.user.UserLoginRequestDto;
import asc.portfolio.ascSb.web.dto.user.UserLoginResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*") //  flutter 에서 rest 접근을 위한 어노테이션 .
public class UserController {

  private final UserService userService;
  private final JwtService jwtService;

  @PostMapping("/signup")
  public ResponseEntity<String> singUp(@RequestBody @Valid UserSignupDto signUpDto, BindingResult bindingResult) {

    log.info("try signup. LoginId={}", signUpDto.getLoginId());

    if (bindingResult.hasErrors()) {
      log.error("Invalid Signup Format");
      return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }

    try {
      Long signUpUserID = userService.signUp(signUpDto);
    } catch (DataIntegrityViolationException e) {
      log.error("Signup Unique violation");
      return new ResponseEntity<>("BAD_REQUEST - Unique violation", HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserLoginRequestDto loginDto, BindingResult bindingResult) {

    log.info("try login. LoginId={}", loginDto.getLoginId());

    if (bindingResult.hasErrors()) {
      log.error("Validation error : Login DTO");
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    UserLoginResponseDto loginRespDto = jwtService.createToken(loginDto.getLoginId(), loginDto.getPassword());

    if (loginRespDto == null) {
      log.error("Unknown User");
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    log.info("Response Login Dto");
    return new ResponseEntity<>(loginRespDto, HttpStatus.OK);
  }

  @RequestMapping("/login-check") //Test
  public ResponseEntity<String> loginCheck(@LoginUser User user) {

    log.info("user.getId()={}", user.getId());
    log.info("user.getLoginId()={}", user.getLoginId());
    log.info("user.getEmail()={}", user.getEmail());

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @RequestMapping("/login-test") //Test
  public ResponseEntity<String> loginCheckWithoutInterceptor(@LoginUser User user) {
    // LoginCheckInterceptor 를 통과하지 않은 Controller

    if (user != null) {
      log.info("user.getId()={}", user.getId());
      log.info("user.getLoginId()={}", user.getLoginId());
      log.info("user.getEmail()={}", user.getEmail());
    } else {
      log.info("user=null");
    }

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @GetMapping("/api/v1")
  public List<UserQrAndNameResponseDto> findQrAndNameById(@LoginUser User user) {

    return userService.userQrAndName(user.getId());
  }
}
