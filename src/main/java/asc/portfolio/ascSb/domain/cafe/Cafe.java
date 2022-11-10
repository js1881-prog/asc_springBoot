package asc.portfolio.ascSb.domain.cafe;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "CAFE")
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_ID", nullable = false)
    private Long id;

    private String cafeName;

    private String cafeArea;

    private String cafeState; // 카페 영업 여부

    private int businessHour; // 영업시간 24시간,12시간

}
