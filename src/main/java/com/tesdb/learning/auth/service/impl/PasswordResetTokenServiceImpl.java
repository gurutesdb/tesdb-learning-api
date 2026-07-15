package com.tesdb.learning.auth.service.impl;

import com.tesdb.learning.auth.entity.PasswordResetToken;
import com.tesdb.learning.auth.repository.PasswordResetTokenRepository;
import com.tesdb.learning.auth.service.PasswordResetTokenService;
import com.tesdb.learning.core.constant.MessageConstants;
import com.tesdb.learning.core.constant.SecurityConstants;
import com.tesdb.learning.core.exception.BadRequestException;
import com.tesdb.learning.core.exception.ResourceNotFoundException;
import com.tesdb.learning.student.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService
{
    //private static final int TOKEN_EXPIRY_MINUTES = 30;

    @Value("${app.security.password-reset-token-expiry-minutes}")
    private Integer passwordResetTokenExpiryMinutes;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private static final Logger log = LoggerFactory.getLogger(PasswordResetTokenServiceImpl.class);

    public PasswordResetTokenServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository)
    {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public PasswordResetToken createToken(Student student)
    {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setStudent(student);
        passwordResetToken.setToken(UUID.randomUUID().toString());
        passwordResetToken.setExpiresAt(LocalDateTime.now().plusMinutes(passwordResetTokenExpiryMinutes));
        log.info("Password reset token generated for '{}'.", student.getEmail());
        return passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public PasswordResetToken validateToken(String token)
    {

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.INVALID_PASSWORD_RESET_TOKEN));

        if (Boolean.TRUE.equals(passwordResetToken.getUsed()))
        {
            throw new BadRequestException(MessageConstants.PASSWORD_RESET_TOKEN_ALREADY_USED);
        }

        if (passwordResetToken.getExpiresAt().isBefore(LocalDateTime.now()))
        {
            throw new BadRequestException(MessageConstants.PASSWORD_RESET_TOKEN_EXPIRED);
        }

        log.info("Password reset token validated.");

        return passwordResetToken;
    }

    @Override
    public void markTokenAsUsed(PasswordResetToken passwordResetToken)
    {
        passwordResetToken.setUsed(true);
        log.info("Password reset token marked as used.");
        passwordResetTokenRepository.save(passwordResetToken);
    }
}
