package asc.portfolio.ascSb.web.controller;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRoleType;
import asc.portfolio.ascSb.jwt.LoginUser;
import asc.portfolio.ascSb.service.fcm.FirebaseCloudMessageService;
import asc.portfolio.ascSb.service.fcm.fcmtoken.FCMTokenService;
import asc.portfolio.ascSb.web.dto.fcm.FCMRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/firebase")
public class FirebaseController {

    private final FCMTokenService fcmTokenService;

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @PostMapping("/cm-token/confirm")
    public ResponseEntity<?> checkFCMToken(@LoginUser User user, @RequestParam String fCMToken) {

        if (user == null) {
            log.info("유저가 일치하지 않습니다");
            return new ResponseEntity<>("User Data incorrect", HttpStatus.BAD_REQUEST);
        }

        if (fCMToken == null) {
            log.info("fCMToken 값이 비어있습니다");
            return new ResponseEntity<>("FCMToken incorrect", HttpStatus.BAD_REQUEST);
        }

        if (user.getRole() == UserRoleType.USER) {
            Boolean confirm = fcmTokenService.confirmToken(user, fCMToken);
            if (confirm)
                return new ResponseEntity<>("OK", HttpStatus.OK);
        }

        if (user.getRole() == UserRoleType.ADMIN) {
            Long id = fcmTokenService.confirmAdminFCMToken(user, fCMToken);
            if (id == null) {
                log.info("admin fcm token verify failed");
                return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }

        log.info("fcm token check error");
        return  new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/fcm")
    public ResponseEntity<?> pushMessage(@RequestBody FCMRequestDto requestDTO) throws IOException {

        log.info("FCM SEND {} {}", requestDTO.getTitle(), requestDTO.getBody());

        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());

        return ResponseEntity.ok().build();
    }


}
