package asc.portfolio.ascSb.service.product;

import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.product.ProductRepository;
import asc.portfolio.ascSb.domain.product.ProductStateType;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import asc.portfolio.ascSb.web.dto.product.ProductDto;
import asc.portfolio.ascSb.web.dto.product.ProductListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    @Override
    public List<ProductListResponseDto> adminSalesManagementOneUser(String userLoginId, String cafeName) {
        Optional<User> user = userRepository.findByLoginId(userLoginId);
        if(user.isPresent()) {
            User userDto = user.get();
            Long id = userDto.getId();
            return productRepository.findProductListByUserIdAndCafeName(id, cafeName).stream()
                    .map(ProductListResponseDto::new)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Product> adminSalesManagementWithStartDate(String cafeName, String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime parse = LocalDateTime.parse(dateString, formatter);
        List<Product> dto = productRepository.findProductListByUserIdAndCafeNameAndStartTime(cafeName, parse);
        return dto;
    }

    @Override
    public Product saveProduct(User user, BootPayOrderDto dto, Orders orders) {
        Product productDto = ProductDto.builder()
                .cafe(user.getCafe())
                .user(user)
                .productState(ProductStateType.SALE)
                .productNameType(orders.getOrderProductName())
                .productPrice(Math.toIntExact(orders.getOrderPrice()))
                .productLabel(orders.getProductLabel())
                .build()
                .toEntity();

        return productRepository.save(productDto);
    }

    @Override
    public void cancelProduct(String productLabel) {
        Product cancelProductInfo = productRepository.findByProductLabelContains(productLabel);
        cancelProductInfo.cancelProduct();
        productRepository.save(cancelProductInfo);
    }
}
