package asc.portfolio.ascSb.domain;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.room.Room;
import asc.portfolio.ascSb.domain.room.RoomRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
@Commit
public class TestDataGeneration {
  //Test 클래스의 @Transactional 은 Rollback 하니 @Commit 으로 설정

  @Autowired
  CafeRepository cafeRepository;

  @Autowired
  RoomRepository roomRepository;

  @Autowired
  UserRepository userRepository;

  @BeforeEach
  public void clearRepository() {
    cafeRepository.deleteAll();
    roomRepository.deleteAll();
    userRepository.deleteAll();
  }

  private void generateCafeRoomData() {

    String[] cafeName = {"tCafe_A", "tCafe_B", "tCafe_C", "tCafe_D", "tCafe_E", "tCafe_F"};
    String[] cafeArea = {
            "서울특별시",
            "부산광역시", "인천광역시", "대구광역시", "대전광역시", "광주광역시", "울산광역시",
            "세종특별자치시",
            "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도",
            "제주특별자치도"};

    for (int i = 0; i < cafeName.length; i++) {
      Cafe cafe = Cafe.builder()
              .cafeName(cafeName[i])
              .cafeArea(cafeArea[i])
              .build();

      cafeRepository.save(cafe);
    }

    generateRoomData();
  }

  private void generateRoomData() {
    List<Cafe> cafeList = cafeRepository.findAll();

    for (Cafe cafe : cafeList) {

      for(int i=0; i < 40; i ++) {

        Room room = Room.builder()
                .seatNumber(i)
                .cafe(cafe)
                .build();

        roomRepository.save(room);
      }
    }
  }

  private void generateUserData() {

    String[] userName = {"tUser_A", "tUser_B", "tUser_C", "tUser_D", "tUser_E", "tUser_F"};

    for (int i = 0; i < userName.length; i++) {
      String userString = userName[i];
      User user = User.builder()
              .loginId(userString + "_login")
              .password(userString + "_password")
              .email(userString + "@gmail.com")
              .name(userString)
              .nickname(userString)
              .build();

      userRepository.save(user);
    }


  }

  @Test
  public void setTestData() {
    generateCafeRoomData();
    generateUserData();
  }
}
