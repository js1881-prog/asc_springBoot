package asc.portfolio.ascSb.service.product;

import asc.portfolio.ascSb.domain.product.Product;
import com.querydsl.core.Tuple;

import java.util.List;

public interface ProductService {
    List<Product> adminSalesManagementWithStartDate(Long id, String cafeName, String dateString);
}
