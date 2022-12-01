package asc.portfolio.ascSb.domain.product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductCustomRepository {

    List<Product> findProductListByUserIdAndCafeNameAndStartTime(Long id, String cafeName, LocalDateTime startTime);
}
