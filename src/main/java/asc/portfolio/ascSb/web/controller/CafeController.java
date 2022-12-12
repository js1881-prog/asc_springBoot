package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.cafe.CafeService;
import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cafe")
@CrossOrigin(origins = "*")
public class CafeController {

  private final CafeService cafeService;

  @GetMapping("")
  public List<CafeResponseDto> respCafeNames() {

    return cafeService.showAllCafeList();
  }

  @Parameter(name = "cafeName", example = "tCafe_A")
  @PostMapping("/change/{cafeName}")
  public ResponseEntity<String> changeReservedUserCafe(@LoginUser User user, @PathVariable String cafeName) {
    if (user.getRole() == UserRoleType.USER) {
      String resultName = cafeService.changeReservedUserCafe(user, cafeName);
      if (resultName != null) {
        return new ResponseEntity<>(resultName, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } else {
      log.info("Admin 유저의 카페 변경은 비활성화 되어 있습니다.");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
