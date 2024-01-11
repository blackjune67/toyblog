package toyblog.june.springbootdev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyblog.june.springbootdev.domain.RefreshToken;
import toyblog.june.springbootdev.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new IllegalArgumentException("알 수 없는 유저입니다."));
    }
}
