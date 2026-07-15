package com.tesdb.learning.auth.service;

import com.tesdb.learning.auth.dto.*;

public interface AuthService
{
    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    CurrentUserResponse getCurrentUser();

    void verifyEmail(VerifyEmailRequest request);

    void forgotPassword(ForgotPasswordRequest request);

    void resetPassword(ResetPasswordRequest request);

}
