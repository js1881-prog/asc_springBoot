package asc.portfolio.ascSb.domain;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.commonenum.product.ProductNameType;
import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.product.ProductRepository;
import asc.portfolio.ascSb.domain.product.ProductStateType;
import asc.portfolio.ascSb.domain.seat.Seat;
import asc.portfolio.ascSb.domain.seat.SeatRepository;
import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.domain.seatreservationinfo.SeatReservationInfoRepository;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.ticket.TicketStateType;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class TestDataGeneration {
  //Test 클래스의 @Transactional 은 Rollback 하니 @Commit 으로 설정

  @Autowired
  CafeRepository cafeRepository;

  @Autowired
  SeatRepository seatRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  SeatReservationInfoRepository seatReservationInfoRepository;

  @Autowired
  TicketRepository ticketRepository;

  @Autowired
  ProductRepository productRepository;

  @BeforeEach
  public void clearRepository() {
    ticketRepository.deleteAll();
    seatRepository.deleteAll();
    cafeRepository.deleteAll();
    userRepository.deleteAll();
  }

  private void generateCafeSeatData() {

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

    generateSeatData();
  }

  private void generateSeatData() {
    List<Cafe> cafeList = cafeRepository.findAll();

    for (Cafe cafe : cafeList) {

      for(int i=0; i < 40; i ++) {

        Seat seat = Seat.builder()
                .seatNumber(i)
                .cafe(cafe)
                .build();

        seatRepository.save(seat);
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
              .role(UserRoleType.USER)
              .build();

      userRepository.save(user);
    }
  }

  private void generateAdminUserData() {
    String userName = "tUserAdmin";

    User user = User.builder()
            .loginId(userName)
            .password(userName + "password")
            .email(userName + "@gmail.com")
            .name(userName)
            .role(UserRoleType.ADMIN)
            .build();

    userRepository.save(user);
  }

  private void generateTicketData() {
    LocalDateTime date = LocalDateTime.now();

    Ticket ticket = Ticket.builder()
            .cafe(cafeRepository.findByCafeNameContains("tCafe_A"))
            .user(userRepository.findByNameContains("tUser_F"))
            .isValidTicket(TicketStateType.VALID)
            .ticketPrice(3000)
            .fixedTermTicket(date)
            .partTimeTicket(0)
            .remainingTime(0)
            .build();
    ticketRepository.save(ticket);
  }

  private void generateProductData() {
    ProductStateType productState;
    ProductNameType productName;

    for(int i=0; i < 20; i ++) {
      if (i % 2 == 0) {
        productState = ProductStateType.SALE;
        productName = ProductNameType.FOUR_WEEK_FIXED_TERM_TICKET;
      } else {
        productState = ProductStateType.CANCEL;
        productName = ProductNameType.WEEK_FIXED_TERM_TICKET;
      }
      Product product = Product.builder()
              .cafe(cafeRepository.findByCafeNameContains("tCafe_A"))
              .productType(productName)
              .user(userRepository.findByNameContains("tUser_F"))
              .productState(productState)
              .description("테스트 product")
              .productPrice(1000 * i)
              .build();
      productRepository.save(product);
    }
  }

  @Test
  public void setTestData() {
    Assertions.assertThat(cafeRepository.count()).isEqualTo(0);
    Assertions.assertThat(seatRepository.count()).isEqualTo(0);
    Assertions.assertThat(userRepository.count()).isEqualTo(0);

    generateCafeSeatData();
    generateUserData();
    generateAdminUserData();
    generateTicketData();
    generateProductData();
  }
}
