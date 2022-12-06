package asc.portfolio.ascSb.config.webconfig;
import asc.portfolio.ascSb.jwt.LoginUserArgumentResolver;
import asc.portfolio.ascSb.web.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final LoginCheckInterceptor loginCheckInterceptor;
  private final LoginUserArgumentResolver loginUserArgumentResolver;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginCheckInterceptor)
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns(
                    "/api/v1/user/signup", "/api/v1/user/login", "/api/v1/user/login-test","/api/v1/seat/**",
                    "/error", "/favicon.ico", "/",
                    "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**");
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginUserArgumentResolver);
  }
}
