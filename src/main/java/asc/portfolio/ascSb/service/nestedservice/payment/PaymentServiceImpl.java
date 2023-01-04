package asc.portfolio.ascSb.service.nestedservice.payment;

import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.service.product.ProductService;
import asc.portfolio.ascSb.service.ticket.TicketService;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionalException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final ProductService productService;
    private final TicketService ticketService;

    // 결제 검증 완료된 데이터 DML 시의 transaction 통일을 위한 method
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean modifyAndAddValidPayment(Orders orders, User user, BootPayOrderDto dto) {
        try {
            orders.completeOrder();
            productService.saveProduct(user, dto, orders);
            ticketService.saveProductToTicket(user, dto, orders);
        } catch (TransactionalException e) {
            log.info("transaction is failed");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
