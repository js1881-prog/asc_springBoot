package asc.portfolio.ascSb.service.order;

import asc.portfolio.ascSb.domain.order.Order;
import asc.portfolio.ascSb.web.dto.order.OrderDto;

public interface OrderService {

    Long saveOrder(OrderDto orderDto);

    Order findReceiptOrderId(Long id);

}
