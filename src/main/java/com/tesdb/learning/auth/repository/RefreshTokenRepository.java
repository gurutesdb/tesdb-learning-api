package com.tesdb.learning.auth.repository;

import com.tesdb.learning.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>
{
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUuid(UUID uuid);
}
