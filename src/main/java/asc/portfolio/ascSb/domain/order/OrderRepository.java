package asc.portfolio.ascSb.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    Orders findByReceiptOrderIdContains(String receiptId);

    Orders findByProductLabelContains(String productLabel);

}
