package asc.portfolio.ascSb.web.interceptor;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.jwt.AuthenticationContext;
import asc.portfolio.ascSb.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

  private final AuthenticationContext authenticationContext;
  private final UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

    User findUser = userService.checkJsonWebToken(jwt);
    if (findUser == null) {
      log.info("Unauthorized access = {}", request.getRequestURL());
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false; // Controller 진행 중지
    }

    log.info("Authorized User={}", findUser.getLoginId());

    authenticationContext.setPrincipal(findUser);

    return true; // Controller 진행
  }
}
