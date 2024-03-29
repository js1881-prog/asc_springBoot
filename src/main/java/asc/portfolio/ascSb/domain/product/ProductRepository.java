package asc.portfolio.ascSb.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {
    Product findByProductLabelContains(String productLabel);

}
