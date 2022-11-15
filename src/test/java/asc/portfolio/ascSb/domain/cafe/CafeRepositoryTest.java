package asc.portfolio.ascSb.domain.cafe;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CafeRepositoryTest {

    @Autowired
    CafeRepository cafeRepository;

    @Test
    public void Room_카페생성기() {

        Cafe cafe = new Cafe();
        cafe.setBusinessHour(24);
        cafe.setCafeArea("서울");
        cafe.setCafeName("알라딘스터디카페");
        cafe.setCafeState("Y");

        cafeRepository.save(cafe);
    }
}
