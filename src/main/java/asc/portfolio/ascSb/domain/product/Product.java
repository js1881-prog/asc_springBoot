package asc.portfolio.ascSb.domain.product;


import asc.portfolio.ascSb.domain.BaseTimeEntity;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.commonenum.ProductNameType;
import asc.portfolio.ascSb.domain.user.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID", nullable = false)
    private Long id;

    @JoinColumn(name = "C_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="USER_ID")
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private ProductStateType productState;

    @Enumerated(EnumType.STRING)
    private ProductNameType productName;

    @Column(name = "DE")
    private String description;

    @Column(name = "P_P")
    private Integer productPrice;

    @Builder
    public Product(Cafe cafe, User user, ProductStateType productState, ProductNameType productName,
                   String description, Integer productPrice)
    {
        this.cafe = cafe;
        this.user = user;
        this.productState = productState;
        this.productName = productName;
        this.description = description;
        this.productPrice = productPrice;
    }
}
