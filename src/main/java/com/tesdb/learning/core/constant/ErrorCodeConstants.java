package com.tesdb.learning.core.constant;

public class ErrorCodeConstants
{
    private ErrorCodeConstants()
    {
    }

    // Authentication
    public static final String AUTH_INVALID_CREDENTIALS = "AUTH_001";
    public static final String AUTH_EMAIL_NOT_VERIFIED = "AUTH_002";
    public static final String AUTH_INVALID_EMAIL_VERIFICATION_TOKEN = "AUTH_003";
    public static final String AUTH_EMAIL_VERIFICATION_TOKEN_EXPIRED = "AUTH_004";
    public static final String AUTH_INVALID_PASSWORD_RESET_TOKEN = "AUTH_005";
    public static final String AUTH_PASSWORD_RESET_TOKEN_EXPIRED = "AUTH_006";
    public static final String AUTH_PASSWORD_RESET_TOKEN_ALREADY_USED = "AUTH_007";
}
