package com.tesdb.learning.auth.service.impl;

import com.tesdb.learning.auth.entity.EmailVerificationToken;
import com.tesdb.learning.auth.repository.EmailVerificationTokenRepository;
import com.tesdb.learning.auth.service.EmailVerificationTokenService;
import com.tesdb.learning.core.exception.BadRequestException;
import com.tesdb.learning.core.exception.InvalidTokenException;
import com.tesdb.learning.core.exception.TokenExpiredException;
import com.tesdb.learning.core.properties.FrontendProperties;
import com.tesdb.learning.notification.service.EmailService;
import com.tesdb.learning.notification.service.EmailTemplateService;
import com.tesdb.learning.student.entity.Student;
import com.tesdb.learning.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenServiceImpl  implements EmailVerificationTokenService
{
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    private final StudentRepository studentRepository;

    private final EmailService emailService;

    private final FrontendProperties frontendProperties;

    private final EmailTemplateService emailTemplateService;

    @Override
    public EmailVerificationToken createVerificationToken(Student student)
    {
        EmailVerificationToken verificationToken =
                new EmailVerificationToken();

        verificationToken.setStudent(student);

        verificationToken.setToken(UUID.randomUUID().toString());

        verificationToken.setExpiresAt(LocalDateTime.now().plusHours(24));

        //return emailVerificationTokenRepository.save(verificationToken);

        EmailVerificationToken savedToken = emailVerificationTokenRepository.save(verificationToken);

        String verificationLink =
                frontendProperties.getBaseUrl()
                        + "/verify-email?token="
                        + savedToken.getToken();

        String body = emailTemplateService.buildVerificationEmail(student.getFirstName(), verificationLink);

        emailService.sendEmail(student.getEmail(), "Verify Your TESDB Learning Account", body);

        return savedToken;
    }

    @Override
    public EmailVerificationToken verifyToken(String token)
    {
        EmailVerificationToken verificationToken =
                emailVerificationTokenRepository
                        .findByToken(token)
                        .orElseThrow(() ->
                                new InvalidTokenException("Invalid verification token."));

        if (verificationToken.getVerified())
        {
            throw new BadRequestException("Email is already verified.");
        }

        if (verificationToken.getExpiresAt().isBefore(LocalDateTime.now()))
        {
            throw new TokenExpiredException("Verification token has expired.");
        }

        Student student = verificationToken.getStudent();

        student.setEmailVerified(true);

        studentRepository.save(student);

        verificationToken.setVerified(true);

        return emailVerificationTokenRepository.save(verificationToken);
    }
}
