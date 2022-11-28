package asc.portfolio.ascSb.domain.order;

import asc.portfolio.ascSb.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ORDER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "O_ID", nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStateType orderStateType;

    private String username;

    private String orderProductName;

    private Long orderPrice;

    private Long receiptOrderId;

    @Builder
    private Order(OrderStateType orderStateType, String username, String orderProductName, Long orderPrice, Long receiptOrderId) {
        this.orderStateType = orderStateType;
        this.username = username;
        this.orderProductName = orderProductName;
        this.orderPrice = orderPrice;
        this.receiptOrderId = receiptOrderId;
    }

    /**
     * 주문 정상적으로 완료
     */
    public void completeOrder() {
        this.orderStateType = OrderStateType.COMP;
    }

    /**
     * 주문 실패
     */
    public void failOrder() {
        this.orderStateType = OrderStateType.FAIL;
    }
}
