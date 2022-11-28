package asc.portfolio.ascSb.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByReceiptOrderIdContains(Long id);

}
