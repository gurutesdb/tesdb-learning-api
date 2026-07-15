package com.tesdb.learning.auth.repository;

import com.tesdb.learning.auth.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>
{
    Optional<PasswordResetToken> findByUuid(UUID uuid);

    Optional<PasswordResetToken> findByToken(String token);
}
