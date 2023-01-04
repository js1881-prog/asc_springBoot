package asc.portfolio.ascSb.service.nestedservice.payment;

import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;

public interface PaymentService {

    boolean modifyAndAddValidPayment(Orders orders, User user, BootPayOrderDto dto);
}
