package asc.portfolio.ascSb.web.dto.product;

import asc.portfolio.ascSb.commonenum.product.ProductNameType;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.product.ProductStateType;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductListResponseDto {

    private ProductStateType productState;
    private ProductNameType productNameType;
    private String productNameTypeString;
    private String description;
    private Integer productPrice;
    private String productLabel;

    public ProductListResponseDto(Product product) {
        this.productState = product.getProductState();
        this.productNameType = product.getProductNameType();
        this.description = product.getDescription();
        this.productPrice = product.getProductPrice();
        this.productLabel = product.getProductLabel();
        this.productNameTypeString = product.getProductNameType().name();
    }
}
