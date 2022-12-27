package asc.portfolio.ascSb.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Component
public class JwtTokenProvider {

//  private final String secretKey;
  private final Key secretKey;
  private final long expireTime;

  public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                          @Value("${jwt.expiration-in-seconds}") Long expireTime) {

    this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    this.expireTime = expireTime * 1000;
  }

  public String createAccessToken(String subject) {
    Date now = new Date();
    Date expireDate = new Date(now.getTime() + expireTime);

    return Jwts.builder()
            .setSubject(subject)
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
  }

  public String createRefreshToken(String subject) {
    Calendar addDays = Calendar.getInstance();
    addDays.setTime(new Date());
    addDays.add(Calendar.DATE, 14);
    Date expireDate = addDays.getTime();

    return Jwts.builder()
            .setSubject(subject)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
  }

  public String extractSubject(String token) {
    try {
      return Jwts.parserBuilder()
              .setSigningKey(secretKey)
              .build()
              .parseClaimsJws(token)
              .getBody()
              .getSubject();
    } catch (ExpiredJwtException e) {
      throw new IllegalStateException("만료된 JWT 토큰입니다.");
    } catch (JwtException e) {
      throw new IllegalStateException("올바르지 않은 JWT 토큰입니다.");
    }
  }
}