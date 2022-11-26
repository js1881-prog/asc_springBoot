package asc.portfolio.ascSb.web.dto.product;

import asc.portfolio.ascSb.domain.commonenum.ProductNameType;
import asc.portfolio.ascSb.domain.product.ProductStateType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ProductSelectResponseDto {

    private String loginId;
    private ProductStateType productStateType;
    private ProductNameType productNameType;
    private String description;
    private Integer productPrice;

    private Integer productPriceSum;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public ProductSelectResponseDto(String loginId, ProductStateType productStateType, ProductNameType productNameType,
                                    String description, Integer productPrice, Integer productPriceSum,
                                    LocalDateTime createDate, LocalDateTime modifiedDate)
    {
        this.loginId = loginId;
        this.productStateType = productStateType;
        this.productNameType = productNameType;
        this.description = description;
        this.productPrice = productPrice;
        this.productPriceSum = productPriceSum;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public ProductSelectResponseDto(ProductSelectResponseDto entity) {
        this.loginId = entity.getLoginId();
        this.productStateType = entity.getProductStateType();
        this.productNameType = entity.getProductNameType();
        this.description = entity.getDescription();
        this.productPrice = entity.getProductPrice();
        this.productPriceSum = entity.getProductPriceSum();
        this.createDate = entity.getCreateDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
