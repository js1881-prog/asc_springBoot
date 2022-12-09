package asc.portfolio.ascSb.service.product;

import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import asc.portfolio.ascSb.web.dto.product.ProductListResponseDto;

import java.util.List;

public interface ProductService {

    List<ProductListResponseDto> adminSalesManagementOneUser(String userLoginId, String cafeName);

    List<Product> adminSalesManagementWithStartDate(String cafeName, String dateString);

    Product saveProduct(User user, BootPayOrderDto dto, Orders orders);

    void cancelProduct(String productLabel);

}
