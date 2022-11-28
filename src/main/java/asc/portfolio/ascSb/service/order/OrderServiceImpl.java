package asc.portfolio.ascSb.service.order;


import asc.portfolio.ascSb.domain.order.Order;
import asc.portfolio.ascSb.domain.order.OrderRepository;
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

    private final OrderRepository orderRepository;


    @Override
    public Long saveOrder(OrderDto orderDto) {

        Order order = orderDto.toEntity();
        Order saveOrder = orderRepository.save(order);

        return saveOrder.getReceiptOrderId();
    }

    @Override
    public Order findReceiptOrderId(Long id) {
        return orderRepository.findByReceiptOrderIdContains(id);
    }
}
