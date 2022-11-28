package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.service.product.order.Order;
import asc.portfolio.ascSb.service.order.OrderService;
import asc.portfolio.ascSb.web.bootpay.Bootpay;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import asc.portfolio.ascSb.web.dto.bootpay.request.Cancel;
import asc.portfolio.ascSb.web.dto.order.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class BootPayController {

    private final OrderService orderService;


    @PostMapping("/api/v1/pay")
    public ResponseEntity<String> pay(@RequestBody OrderDto dto) {
        try {
            Long orderId = orderService.saveOrder(dto);
            log.info("주문번호={}", orderId);
        } catch (Exception e) {
            return new ResponseEntity<>("유효하지 않은 주문입니다.", HttpStatus.BAD_REQUEST);
        }
       return new ResponseEntity<>("OK", HttpStatus.OK);
    }



    @GetMapping("/api/v1/pay/confirm")
    public ResponseEntity confirmPay(
            @RequestParam("receipt_id") String receipt_id) throws Exception {

        String getDataJson = "";
        BootPayOrderDto dto = null;
        String rest_application_id = "발급 받은 id";
        String private_key = "발급 받은 인증키";

        Bootpay api = new Bootpay(
                rest_application_id,
                private_key
        );
        api.getAccessToken();
        try {
            HttpResponse res = api.verify(receipt_id);
            getDataJson = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(getDataJson);

            ObjectMapper objectMapper = new ObjectMapper();
            dto = objectMapper.readValue(getDataJson, BootPayOrderDto.class);
            System.out.println(dto);

        } catch (Exception e) {
            e.printStackTrace();
        }

        long orderId = Long.parseLong(dto.getData().getOrder_id());
        Order order = orderService.findReceiptOrderId(orderId);
        int price = Math.toIntExact(order.getOrderPrice());

        if (dto.getStatus() == 200) {

            //status가 1이고
            if (dto.getData().getPrice() == price && dto.getData().getStatus() == 1) {
                //결제 완료
                //orderService.completeOrder(orderId);
                return ResponseEntity.ok("결제완료");
            }
        }

        //서버 검증 오류시
        Cancel cancel = new Cancel();
        cancel.receiptId = receipt_id;
        cancel.name = "관리자";
        cancel.reason = "서버 검증 오류";

        //결제 오류 로그
        //orderService.failOrder(orderId);
        String cancelDataJson = "";
        try {
            HttpResponse res = api.receiptCancel(cancel);
            cancelDataJson = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(cancelDataJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("결제실패");
    }

}
