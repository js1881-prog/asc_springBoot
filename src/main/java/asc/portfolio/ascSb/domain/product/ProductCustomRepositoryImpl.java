package asc.portfolio.ascSb.domain.product;

import asc.portfolio.ascSb.domain.cafe.QCafe;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

import static asc.portfolio.ascSb.domain.cafe.QCafe.cafe;
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
                .leftJoin(product.cafe, cafe)
                .where(cafe.cafeName.eq(cafeName),
                        product.createDate.between(startTime, now),
                        product.productState.eq(ProductStateType.SALE)
                )
                .fetch();
    }

    @Override
    public List<Product> findProductListByUserIdAndCafeName(Long userId, String cafeName) {
        QProduct qProduct = new QProduct("subQ");
        QCafe qCafe = new QCafe("subC");

        return query.select(qProduct)
                .from(qProduct)
                .leftJoin(qProduct.cafe, qCafe)
                .where(qProduct.user.id.eq(userId), qCafe.cafeName.eq(cafeName))
                .fetch();
    }
}
