package asc.portfolio.ascSb.service.cafe;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

  private final CafeRepository cafeRepository;

  @Override
  public List<CafeResponseDto> showAllCafeList() {
    return cafeRepository.findAllCafeNameAndArea();
  }

  @Override
  public Optional<Cafe> changeReservedUserCafe(User user, String cafeName) {

    Optional<Cafe> findCafeOpt = cafeRepository.findByCafeName(cafeName);

    if (findCafeOpt.isEmpty()) {
      log.error("Unknown Cafe Name = {}", cafeName);
      return Optional.empty();
    } else {
      findCafeOpt.ifPresent(c -> user.changeCafe(c));
      return findCafeOpt;
    }
  }
}
