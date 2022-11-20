package asc.portfolio.ascSb.domain.cafe;

import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;

import java.util.List;

public interface CafeCustomRepository {

  public List<CafeResponseDto> findAllCafeNameAndArea();
}
