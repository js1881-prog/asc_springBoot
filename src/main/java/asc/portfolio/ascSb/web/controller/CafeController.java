package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.cafe.CafeService;
import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping("/change/{cafeName}")
  public ResponseEntity<String> changeReservedUserCafe(@LoginUser User user, @PathVariable String cafeName) {

    boolean result = cafeService.changeReservedUserCafe(user, cafeName);
    if (result) {
      return new ResponseEntity<>("Success", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
  }
}
