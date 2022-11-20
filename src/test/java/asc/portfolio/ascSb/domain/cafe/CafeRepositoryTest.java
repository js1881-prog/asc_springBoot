package asc.portfolio.ascSb.domain.cafe;


import asc.portfolio.ascSb.web.dto.cafe.CafeResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CafeRepositoryTest {

    @Autowired
    CafeRepository cafeRepository;

    @Test
    public void Cafe_카페생성기() {

        Cafe cafe = new Cafe();
        cafe.setBusinessHour(24);
        cafe.setCafeArea("서울");
        cafe.setCafeName("알라딘스터디카페");
        cafe.setCafeState("Y");

        cafeRepository.save(cafe);
    }

    @Test
    public void findAllCafeDto() {

        List<CafeResponseDto> allCafeDto = cafeRepository.findAllCafeNameAndArea();

//        for (int i = 0; i < allCafeDto.size(); i++) {
//
//            System.out.println("allCafeDto = " + allCafeDto.get(i).getCafeNames() + ", " + allCafeDto.get(i).getCafeArea());
//
//        }
    }
}
