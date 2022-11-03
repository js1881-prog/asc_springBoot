package asc.portfolio.ascSb.domain.room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROOM_ID", nullable = false)
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "cafe_id")
//    private Cafe cafe;

    @Column(name = "CAFE_NAME")
    private String studyCafeName;

    @Column(name = "STATE") // 40개의 좌석에 대한 State
    private String seatState; // 40개의 String, 0101010~~0 (40개), 0=미사용중, 1=사용중

    @Column(name = "IS_OPENED") // 영업여부
    @ColumnDefault("true") // default 24시간
    private boolean isOpened;

    @Column(name = "OPEN_TIME") // 영업시간
    @ColumnDefault("24") // default 24시간
    private int openTime;

    @Column(name = "ADMIN") // 관리자 id (사장님) (후에 관리테이블 추가 후 사용)
    private Long admin;

}
