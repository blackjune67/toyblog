package toyblog.june.springbootdev.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import toyblog.june.springbootdev.domain.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // * 토큰 생성
    private String makeToken(Date expiry, User user) {
//        Date now = new Date();
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        SecretKey key = Keys.hmacShaKeyFor(environment.getProperty("jwt.token.secret").getBytes(StandardCharsets.UTF_8));
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        /*return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();*/

        return Jwts.builder()
                .issuer(jwtProperties.getIssuer())
                .issuedAt(new Date())
                .expiration(expiry)
                .subject(user.getEmail())
                .claim("id", user.getId())
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    // * 유효성 검증
    public boolean validToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        try {
            /*Jwts.parser()
                    .decryptWith(secretKey)
                    .build()
                    .parseEncryptedClaims(token);*/
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // * 토큰 기반으로 인증 정보 가져오는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails
                        .User(claims.getSubject(), "", authorities), token, authorities);
    }

    // * 토큰 기반 User ID 갖고오는 메서드
    public Long getUserId(String toeken) {
        Claims claims = getClaims(toeken);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
//                .setSigningKey(jwtProperties.getSecretKey())
//                .parseClaimsJws(token)
//                .getBody();
    }
}
