package asc.portfolio.ascSb.service.cafe;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;

import java.util.List;
import java.util.Optional;

public interface CafeService {

  public List<CafeResponseDto> showAllCafeList();

  public String changeReservedUserCafe(User user, String cafeName);
}
