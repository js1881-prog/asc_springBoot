package asc.portfolio.ascSb.service.order;

import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.order.OrderDto;

public interface OrderService {

    Long saveOrder(User user, OrderDto orderDto);

    Orders findReceiptOrderId(String receiptId);

    Orders findReceiptIdToProductLabel(String productLabel);

}
