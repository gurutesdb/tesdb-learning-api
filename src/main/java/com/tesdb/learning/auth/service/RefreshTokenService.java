package com.tesdb.learning.auth.service;

import com.tesdb.learning.auth.entity.RefreshToken;
import com.tesdb.learning.core.security.model.CustomUserDetails;

import java.util.Optional;

public interface RefreshTokenService
{
    RefreshToken createRefreshToken(CustomUserDetails user, String token);

    Optional<RefreshToken> findByToken(String token);

    boolean isValid(RefreshToken refreshToken);

    void revokeToken(String token);

    RefreshToken verifyRefreshToken(String token);
}
