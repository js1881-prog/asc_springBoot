package asc.portfolio.ascSb.service.cafe;

import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

  private final CafeRepository cafeRepository;

  @Override
  public List<CafeResponseDto> showAllCafeList() {
    return cafeRepository.findAllCafeNameAndArea();
  }
}
