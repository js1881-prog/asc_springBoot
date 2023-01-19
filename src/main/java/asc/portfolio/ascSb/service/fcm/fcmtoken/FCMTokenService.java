package asc.portfolio.ascSb.service.fcm.fcmtoken;

import asc.portfolio.ascSb.domain.user.User;

public interface FCMTokenService {
    Long confirmAdminFCMToken(User user, String adminFCMToken);
    Boolean isAdminHasToken(User user, String adminFCMToken);
    Boolean confirmToken(User user, String userFCMToken);
    String adminFindSpecificToken(String userId);
}
