package asc.portfolio.ascSb.domain.cafe;
import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Setter // test를 위한 setter 나중에 제거
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "CAFE")
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ID", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cafeName;

    private String cafeArea;

    private String cafeState; // 카페 영업 여부

    private int businessHour; // 영업시간 24시간,12시간

    @Builder
    public Cafe(String cafeName, String cafeArea, String cafeState, int businessHour) {
        this.cafeName = cafeName;
        this.cafeArea = cafeArea;
        this.cafeState = cafeState;
        this.businessHour = businessHour;
    }
}
