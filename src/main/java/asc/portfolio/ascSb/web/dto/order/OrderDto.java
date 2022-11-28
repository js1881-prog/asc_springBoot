package asc.portfolio.ascSb.web.dto.order;

import asc.portfolio.ascSb.service.product.order.Order;
import asc.portfolio.ascSb.service.product.order.OrderStateType;
import lombok.Builder;
import lombok.Data;


@Data
public class OrderDto {

    private OrderStateType orderStateType;
    private String username;
    private String orderProductName;
    private Long orderPrice;
    private Long receiptOrderId;

    @Builder
    public OrderDto(OrderStateType orderStateType, String username, String orderProductName, Long orderPrice, Long receiptOrderId) {
        this.orderStateType = orderStateType;
        this.username = username;
        this.orderProductName = orderProductName;
        this.orderPrice = orderPrice;
        this.receiptOrderId = receiptOrderId;
    }

    public Order toEntity() {
        return Order.builder()
                .orderStateType(orderStateType)
                .username(username)
                .orderProductName(orderProductName)
                .orderPrice(orderPrice)
                .receiptOrderId(receiptOrderId)
                .build();
    }
}
