package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.cafe.CafeService;
import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    Optional<Cafe> optionalResult = cafeService.changeReservedUserCafe(user, cafeName);
    return optionalResult.map(cafe -> new ResponseEntity<>(cafe.getCafeName(),
            HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
  }
}
