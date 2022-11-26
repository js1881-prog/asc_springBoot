package asc.portfolio.ascSb.domain.product;

import asc.portfolio.ascSb.web.dto.product.ProductSelectResponseDto;
import com.querydsl.core.Tuple;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductCustomRepository {

    List<Product> findProductListByUserIdAndCafeNameAndStartTime(Long id, String cafeName, LocalDateTime startTime);
}
