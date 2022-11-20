package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.service.cafe.CafeService;
import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
