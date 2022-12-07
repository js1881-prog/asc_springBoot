package asc.portfolio.ascSb.web.dto.product;

import asc.portfolio.ascSb.commonenum.product.ProductNameType;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.order.OrderStateType;
import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.product.ProductStateType;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDto {

    private Cafe cafe;
    private User user;
    private ProductStateType productState;
    private ProductNameType productNameType;

    private String productNameTypeString;
    private String description;
    private Integer productPrice;
    private String productLabel;


    @Builder
    public ProductDto(Cafe cafe, User user, ProductStateType productState, ProductNameType productNameType,
                      String description, Integer productPrice, String productLabel) {
        this.cafe = cafe;
        this.user = user;
        this.productState = productState;
        this.productNameType = productNameType;
        this.description = description;
        this.productPrice = productPrice;
        this.productLabel = productLabel;
        this.productNameTypeString = productNameType.name();
    }

    public Product toEntity() {
        return Product.builder()
                .cafe(cafe)
                .user(user)
                .productState(productState)
                .productNameType(productNameType)
                .description(description)
                .productPrice(productPrice)
                .productLabel(productLabel)
                .build();
    }
}
