package asc.portfolio.ascSb.service.cafe;

import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CafeService {

  public List<CafeResponseDto> showAllCafeList();
}
