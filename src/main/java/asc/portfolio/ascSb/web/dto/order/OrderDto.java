package asc.portfolio.ascSb.web.dto.order;

import asc.portfolio.ascSb.commonenum.product.ProductNameType;
import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.order.OrderStateType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDto {

    private OrderStateType orderStateType;
    private String userId;
    private ProductNameType orderProduct;
    private Long orderPrice;
    private String receiptOrderId;
    private String productLabel;

    @Builder
    public OrderDto(OrderStateType orderStateType, String userId, ProductNameType orderProduct, Long orderPrice,
                    String receiptOrderId, String productLabel) {
        this.orderStateType = orderStateType;
        this.userId = userId;
        this.orderProduct = orderProduct;
        this.orderPrice = orderPrice;
        this.receiptOrderId = receiptOrderId;
        this.productLabel = productLabel;
    }

    public Orders toEntity() {
        return Orders.builder()
                .orderStateType(orderStateType)
                .userId(userId)
                .productLabel(productLabel)
                .orderProductName(orderProduct)
                .orderPrice(orderPrice)
                .receiptOrderId(receiptOrderId)
                .build();
    }
}
