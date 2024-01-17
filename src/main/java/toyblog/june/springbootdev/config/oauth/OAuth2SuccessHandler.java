package toyblog.june.springbootdev.config.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import toyblog.june.springbootdev.config.jwt.TokenProvider;
import toyblog.june.springbootdev.domain.RefreshToken;
import toyblog.june.springbootdev.domain.User;
import toyblog.june.springbootdev.repository.RefreshTokenRepository;
import toyblog.june.springbootdev.service.UserService;
import toyblog.june.springbootdev.util.CookieUtil;

import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);
    public static final String REDIRECT_PATH = "/articles";

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        User user = userService.findByEmail((String) oAuth2User.getAttributes().get("email"));

        /**
         * 리프레시 토큰 생성 -> 저장 -> 쿠키에 저장
         * */
        String refreshToken = tokenProvider.generateToken(user, REFRESH_TOKEN_DURATION);
        // * 리프레쉬 토큰을 DB에 저장
        RefreshToken refreshTokenToSave = refreshTokenRepository.findByUserId(user.getId())
                .map(entity -> entity.update(refreshToken))
                .orElse(new RefreshToken(user.getId(), refreshToken));
        refreshTokenRepository.save(refreshTokenToSave);

        // * 생성된 리프레쉬토큰 쿠키 저장
        addRefreshTokenToCookie(request, response, refreshToken);

        // * 엑세스 토큰 생성 후 패스에 엑세스 토큰 추가
        String accessToken = tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION);
        log.info("accessToken = " + accessToken);

        // * 엑세스 토큰 패스 추가
        String targetUrl = getTargetUrl(accessToken);
        log.info("targetUrl = " + targetUrl);

        // * 인증관련 설정값 및 쿠키 제거
        clearAuthenticationAttributes(request);
        oAuth2AuthorizationRequestBasedOnCookieRepository.removeAuthorizationRequestCookies(request, response);

        // * 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    public void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token", token)
                .build()
                .toUriString();
    }
}
