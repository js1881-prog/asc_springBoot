package asc.portfolio.ascSb.domain.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

import static asc.portfolio.ascSb.domain.product.QProduct.product;

@RequiredArgsConstructor
@Repository
@Getter
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public List<Product> findProductListByUserIdAndCafeNameAndStartTime(String cafeName, LocalDateTime startTime) {
        LocalDateTime now = LocalDateTime.now();
        return query
                .select(product)
                .from(product)
                .where(product.cafe.cafeName.eq(cafeName),
                        product.createDate.between(startTime, now))
                .fetch();
    }

    @Override
    public List<Product> findProductListByUserIdAndCafeName(Long userId, String cafeName) {
        QProduct qProduct = new QProduct("subQ");

        return query.select(qProduct)
                .from(qProduct)
                .where(qProduct.user.id.eq(userId), qProduct.cafe.cafeName.eq(cafeName))
                .fetch();
    }
}
