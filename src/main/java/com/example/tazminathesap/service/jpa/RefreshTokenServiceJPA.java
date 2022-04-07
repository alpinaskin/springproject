package com.example.tazminathesap.service.jpa;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.example.tazminathesap.exception.TokenRefreshException;
import com.example.tazminathesap.model.RefreshToken;
import com.example.tazminathesap.repository.RefreshTokenRepository;
import com.example.tazminathesap.repository.UserRepository;
import com.example.tazminathesap.service.RefreshTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenServiceJPA implements RefreshTokenService {
    //@Value("${bezkoder.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs = 86400000L;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token süresi doldu. Yeniden giriş yapın");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
