package com.tesdb.learning.auth.repository;

import com.tesdb.learning.auth.entity.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long>
{
    Optional<EmailVerificationToken> findByToken(String token);

    Optional<EmailVerificationToken> findByUuid(UUID uuid);
}
