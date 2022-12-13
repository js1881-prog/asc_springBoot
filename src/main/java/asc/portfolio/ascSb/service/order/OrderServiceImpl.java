package asc.portfolio.ascSb.service.order;


import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.order.OrdersRepository;
import asc.portfolio.ascSb.domain.order.OrderStateType;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.order.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;

    @Override
    public Long saveOrder(User user, OrderDto orderDto) {
        orderDto.setUserId(user.getLoginId());
        orderDto.setOrderStateType(OrderStateType.ORDER);
        Orders orders = orderDto.toEntity();
        Orders saveOrders = ordersRepository.save(orders);
        return saveOrders.getId();
    }

    @Override
    public Orders findReceiptOrderId(String receiptId) {
        return ordersRepository.findByReceiptOrderIdContains(receiptId);
    }

    @Override
    public Orders findReceiptIdToProductLabel(String productLabel) {
        return ordersRepository.findByProductLabelContains(productLabel);
    }
}
