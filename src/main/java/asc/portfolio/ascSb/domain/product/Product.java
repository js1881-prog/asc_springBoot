package asc.portfolio.ascSb.domain.product;


import asc.portfolio.ascSb.domain.BaseTimeEntity;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter // test를 위한 setter 나중에 제거
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID", nullable = false)
    private Long id;

    @ManyToOne
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="USER_ID")
    private User user;

    @Enumerated(EnumType.STRING)
    private ProductStateType productState;

    @Column(name = "P_N")
    private String productName;

    @Column(name = "DE")
    private String description;

    @Column(name = "P_P")
    private Integer productPrice;

}
