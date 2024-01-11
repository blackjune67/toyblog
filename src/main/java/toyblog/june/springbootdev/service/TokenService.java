package toyblog.june.springbootdev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyblog.june.springbootdev.config.jwt.TokenProvider;
import toyblog.june.springbootdev.domain.RefreshToken;
import toyblog.june.springbootdev.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken)) {

        }
        return "";
    }

}
