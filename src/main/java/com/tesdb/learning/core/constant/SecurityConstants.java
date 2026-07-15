package com.tesdb.learning.core.constant;

public final class SecurityConstants
{
    private SecurityConstants()
    {
    }

    /*
     * Token Expiry
     */
    public static final int PASSWORD_RESET_TOKEN_EXPIRY_MINUTES = 30;

    public static final int EMAIL_VERIFICATION_TOKEN_EXPIRY_HOURS = 24;

    /*
     * Authorization Header
     */
    public static final String AUTHORIZATION = "Authorization";

    public static final String BEARER = "Bearer ";

    /*
     * JWT Claims
     */
    public static final String USER_ID = "userId";

    public static final String USER_TYPE = "userType";



}



