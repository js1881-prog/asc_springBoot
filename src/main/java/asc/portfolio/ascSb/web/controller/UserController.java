package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.service.user.UserService;
import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;
import asc.portfolio.ascSb.web.dto.user.UserSignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*") //  flutter 에서 rest 접근을 위한 어노테이션 .
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<Long> singUp(@RequestBody @Valid UserSignupDto signUpDto, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
    }

    Long signUpUserID = userService.signUp(signUpDto);

    return new ResponseEntity<>(signUpUserID, HttpStatus.OK);
  }

  @GetMapping("/api/v1/{id}")
  public List<UserQrAndNameResponseDto> findQrAndNameById(@PathVariable("id") Long id) {

    return userService.userQrAndName(id);
  }
}
