package asc.portfolio.ascSb.web.dto.fcm.fcmtoken;

import asc.portfolio.ascSb.domain.adminfcmtoken.AdminFCMToken;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminFCMTokenRequestDto {

    private User user;
    private Cafe cafe;
    private String fCMToken;

    @Builder
    public AdminFCMTokenRequestDto(User user, Cafe cafe, String fCMToken) {
        this.user = user;
        this.cafe = cafe;
        this.fCMToken = fCMToken;
    }

    public AdminFCMToken toEntity() {
        return AdminFCMToken.builder()
                .user(user)
                .cafe(cafe)
                .fCMToken(fCMToken)
                .build();
    }
}
