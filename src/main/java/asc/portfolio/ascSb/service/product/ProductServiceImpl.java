package asc.portfolio.ascSb.service.product;

import asc.portfolio.ascSb.commonenum.product.ProductNameType;
import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.product.ProductRepository;
import asc.portfolio.ascSb.domain.product.ProductStateType;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import asc.portfolio.ascSb.web.dto.product.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> adminSalesManagementWithStartDate(Long id, String cafeName, String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime parse = LocalDateTime.parse(dateString, formatter);
        List<Product> dto = productRepository.findProductListByUserIdAndCafeNameAndStartTime(id, cafeName, parse);
        return dto;
    }

    @Override
    public Product saveProduct(User user, BootPayOrderDto dto, Orders orders) {
        Product productDto = ProductDto.builder()
                .cafe(user.getCafe())
                .user(user)
                .productState(ProductStateType.SALE)
                .productNameType(ProductNameType.valueOf(dto.getData().getOrder_name()))
                .productPrice(Math.toIntExact(orders.getOrderPrice()))
                .productLabel(orders.getProductLabel())
                .build()
                .toEntity();
        return productRepository.save(productDto);
    }
}
