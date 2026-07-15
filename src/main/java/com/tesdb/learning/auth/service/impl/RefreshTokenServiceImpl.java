package com.tesdb.learning.auth.service.impl;

import com.tesdb.learning.admin.entity.Admin;
import com.tesdb.learning.admin.repository.AdminRepository;
import com.tesdb.learning.auth.entity.RefreshToken;
import com.tesdb.learning.auth.repository.RefreshTokenRepository;
import com.tesdb.learning.auth.service.RefreshTokenService;
import com.tesdb.learning.core.enums.UserType;
import com.tesdb.learning.core.security.jwt.JwtProperties;
import com.tesdb.learning.core.security.model.CustomUserDetails;
import com.tesdb.learning.student.entity.Student;
import com.tesdb.learning.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService
{
    private final RefreshTokenRepository refreshTokenRepository;

    private final StudentRepository studentRepository;

    private final AdminRepository adminRepository;

    private final JwtProperties jwtProperties;

    @Override
    public RefreshToken createRefreshToken(CustomUserDetails user, String token)
    {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(token);

        refreshToken.setExpiresAt(LocalDateTime.now().plusSeconds(jwtProperties.getRefreshTokenExpiration() / 1000));

        if (user.getUserType() == UserType.STUDENT)
        {
            Student student = studentRepository.findByEmail(user.getEmail()).orElseThrow();
            refreshToken.setStudent(student);
        }
        else
        {
            Admin admin = adminRepository.findByEmail(user.getEmail()).orElseThrow();
            refreshToken.setAdmin(admin);
        }

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token)
    {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public boolean isValid(RefreshToken refreshToken)
    {
        return !refreshToken.getRevoked()
                && !refreshToken.getExpired()
                && refreshToken.getExpiresAt().isAfter(LocalDateTime.now());
    }

    @Override
    public void revokeToken(String token)
    {
        refreshTokenRepository.findByToken(token)
                .ifPresent(refreshToken ->
                {
                    refreshToken.setRevoked(true);
                    refreshTokenRepository.save(refreshToken);
                });
    }

    @Override
    public RefreshToken verifyRefreshToken(String token)
    {
        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Refresh token not found"));

        if (!isValid(refreshToken))
        {
            throw new RuntimeException("Refresh token expired or revoked.");
        }

        return refreshToken;
    }
}
