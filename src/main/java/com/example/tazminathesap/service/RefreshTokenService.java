package com.example.tazminathesap.service;

import java.util.Optional;

import com.example.tazminathesap.model.RefreshToken;

public interface RefreshTokenService {
    public Optional<RefreshToken> findByToken(String token);
    public RefreshToken createRefreshToken(Long userId);
    public RefreshToken verifyExpiration(RefreshToken token);
    public int deleteByUserId(Long userId);
}
