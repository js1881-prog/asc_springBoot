package asc.portfolio.ascSb.web.controller;


import asc.portfolio.ascSb.domain.product.Product;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.product.ProductService;
import asc.portfolio.ascSb.web.dto.seatReservationInfo.SeatReservationInfoSelectResponseDto;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @RequestMapping(value = "/{cafeName}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> productInfoWithConstTerm(@LoginUser User user, @PathVariable String cafeName,
                                                                   @RequestHeader(value = "dataString") String dateString) {
        if(user.getRole() != UserRoleType.ADMIN) {
            log.error("관리자 계정이 아닙니다.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.adminSalesManagementWithStartDate(user.getId(), cafeName, dateString), HttpStatus.OK);
    }
}
