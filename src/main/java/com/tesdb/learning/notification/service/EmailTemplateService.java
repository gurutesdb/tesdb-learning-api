package com.tesdb.learning.notification.service;

public interface EmailTemplateService
{
    String buildVerificationEmail(String studentName, String verificationLink);
}
