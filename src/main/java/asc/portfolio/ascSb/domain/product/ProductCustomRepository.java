package asc.portfolio.ascSb.domain.product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductCustomRepository {

    List<Product> findProductListByUserIdAndCafeNameAndStartTime(String cafeName, LocalDateTime startTime);

    List<Product> findProductListByUserIdAndCafeName(Long userId, String cafeName);

}
