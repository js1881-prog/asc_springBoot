package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.service.jwt.JwtService;
import asc.portfolio.ascSb.service.user.UserService;
import asc.portfolio.ascSb.web.dto.user.UserLoginDto;
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
      return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }

    try {
      Long signUpUserID = userService.signUp(signUpDto);
    } catch (DataIntegrityViolationException e) {
      return new ResponseEntity<>("BAD_REQUEST - Unique violation", HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @Valid UserLoginDto loginDto, BindingResult bindingResult) {

    log.info("try login. LoginId={}", loginDto.getLoginId());

    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }

    //TODO null 체크 코드 삽입
    String token = jwtService.createToken(loginDto.getLoginId(), loginDto.getPassword());
    ObjectMapper mapper = new ObjectMapper();

    HashMap<String, String> responseMap = new HashMap<>();
    responseMap.put("accessToken", token);

    try {
      String responseJson = mapper.writeValueAsString(responseMap);
      return new ResponseEntity<>(responseJson, HttpStatus.OK);
    } catch (IOException e) {
      log.error("Failed to convert map to json");
      return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }
  }
}
