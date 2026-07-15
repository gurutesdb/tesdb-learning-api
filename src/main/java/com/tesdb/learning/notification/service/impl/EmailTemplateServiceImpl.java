package com.tesdb.learning.notification.service.impl;

import com.tesdb.learning.notification.service.EmailTemplateService;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService
{
    @Override
    public String buildVerificationEmail(String studentName, String verificationLink)
    {
        return """
                Hello %s,

                Welcome to TESDB Learning Platform.

                Please verify your email by clicking the link below.

                %s

                This link will expire in 24 hours.

                If you did not create this account,
                please ignore this email.

                Regards,
                TESDB Academy
                """
                .formatted(studentName, verificationLink);
    }
}
