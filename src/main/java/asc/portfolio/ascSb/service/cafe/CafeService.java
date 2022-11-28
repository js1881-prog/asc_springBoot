package asc.portfolio.ascSb.service.cafe;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;

import java.util.List;

public interface CafeService {

  public List<CafeResponseDto> showAllCafeList();

  public boolean changeReservedUserCafe(User user, String cafeName);
}
