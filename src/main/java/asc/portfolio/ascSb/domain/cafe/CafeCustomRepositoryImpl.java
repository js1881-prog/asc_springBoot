package asc.portfolio.ascSb.domain.cafe;

import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static asc.portfolio.ascSb.domain.cafe.QCafe.*;

@RequiredArgsConstructor
@Repository
public class CafeCustomRepositoryImpl implements CafeCustomRepository {

  private final JPAQueryFactory query;

  @Override
  public List<CafeResponseDto> findAllCafeNameAndArea() {
    return query
            .select(Projections.constructor(CafeResponseDto.class, cafe.cafeName, cafe.cafeArea))
            .from(cafe)
            .orderBy(cafe.cafeArea.asc())
            .fetch();
  }
}
