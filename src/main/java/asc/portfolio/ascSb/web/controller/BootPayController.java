package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.order.OrderService;
import asc.portfolio.ascSb.bootpay.Bootpay;
import asc.portfolio.ascSb.service.product.ProductService;
import asc.portfolio.ascSb.service.ticket.TicketService;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import asc.portfolio.ascSb.web.dto.order.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.bootpay.model.request.Cancel;
import kr.co.bootpay.model.response.ResDefault;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/pay")
public class BootPayController {

    private final OrderService orderService;

    private final ProductService productService;

    private final TicketService ticketService;

    @PostMapping("/order")
    public ResponseEntity<String> pay(@LoginUser User user, @RequestBody OrderDto dto) {
        try {
            Long receiptOrderId = orderService.saveOrder(user, dto);
            log.info("주문번호={}", receiptOrderId);
        } catch (Exception e) {
            return new ResponseEntity<>("유효하지 않은 주문입니다.", HttpStatus.BAD_REQUEST);
        }
       return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity confirmPay(
            @LoginUser User user,
            @RequestParam("receipt-id") String receipt_id) throws Exception {

        String getDataJson = "";
        BootPayOrderDto dto = null;
        String rest_application_id = "";
        String private_key = "";

        Bootpay api = new Bootpay(
                rest_application_id,
                private_key
        );

        try {
            ResDefault<HashMap<String, Object>> token = api.getAccessToken();
            System.out.println(token.toJson());
            ResDefault<HashMap<String, Object>> check = api.verify(receipt_id);
            getDataJson = check.toJson();
            ObjectMapper objectMapper = new ObjectMapper();
            dto = objectMapper.readValue(getDataJson, BootPayOrderDto.class);
            log.info("결제 토큰 검증완료 data = {}", check.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String orderReceiptId = dto.getData().getReceipt_id();
        Orders orders = orderService.findReceiptOrderId(orderReceiptId);
        log.info("findOrders완료");
        System.out.println(orders.getReceiptOrderId());
        int price = Math.toIntExact(orders.getOrderPrice());
        System.out.println(price);

        if (dto.getStatus() == 200 && dto.getData().getPrice() == price && dto.getData().getStatus() == 1
        && Objects.equals(orders.getReceiptOrderId(), dto.getData().getReceipt_id())) {
            log.info("검증완료");
            // orders 상태 Done(완료), Product에 제품추가, Ticket에 이용권추가
            orders.completeOrder();
            productService.saveProduct(user, dto, orders);
            Long ticketSaveResult = ticketService.saveProductToTicket(user, dto, orders);
            if(ticketSaveResult == 0L) {
                return ResponseEntity.badRequest().body("보유중인 티켓이 존재합니다.");
            }
            return ResponseEntity.ok("결제완료");
        }

        //서버 검증 오류시
//        Cancel cancel = new Cancel();
//        cancel.receiptId = receipt_id;
//        cancel.name = "관리자";
//        cancel.reason = "서버 검증 오류";
//        orders.failOrder();
//
//        //결제 오류 로그
//        //orderService.failOrder(orderId);
//        String cancelDataJson = "";
//        try {
//            ResDefault<HashMap<String, Object>> cancelRes = api.receiptCancel(cancel);
//            cancelDataJson = cancelRes.toJson();
//            log.info("결제실패 {}", cancelDataJson);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return ResponseEntity.badRequest().body("결제실패"); // 환불
    }
}
