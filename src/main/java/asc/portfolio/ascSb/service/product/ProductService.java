package asc.portfolio.ascSb.service.product;

import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import com.querydsl.core.Tuple;

import java.util.List;

public interface ProductService {
    List<Product> adminSalesManagementWithStartDate(Long id, String cafeName, String dateString);

    Product saveProduct(User user, BootPayOrderDto dto, Orders orders);

}
