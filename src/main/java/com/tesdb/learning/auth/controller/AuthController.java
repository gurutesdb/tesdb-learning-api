package com.tesdb.learning.auth.controller;

import com.tesdb.learning.auth.dto.*;
import com.tesdb.learning.auth.service.AuthService;
import com.tesdb.learning.core.constant.MessageConstants;
import com.tesdb.learning.core.response.ResponseBuilder;
import com.tesdb.learning.core.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthService authService;

    @PostMapping("/login")
    public SuccessResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request)
    {
        LoginResponse response = authService.login(request);
        return ResponseBuilder.success(MessageConstants.LOGIN_SUCCESS, response);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request)
    {
        return authService.refreshToken(request);
    }

    @GetMapping("/me")
    public CurrentUserResponse me() {
        return authService.getCurrentUser();
    }

    @PostMapping("/verify-email")
    public SuccessResponse<Void> verifyEmail(@Valid @RequestBody VerifyEmailRequest request)
    {
        authService.verifyEmail(request);
        return ResponseBuilder.success(MessageConstants.EMAIL_VERIFIED);
    }

    @PostMapping("/forgot-password")
    public SuccessResponse<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request)
    {
        authService.forgotPassword(request);
        return ResponseBuilder.success(MessageConstants.PASSWORD_RESET_EMAIL_SENT);
    }

    @PostMapping("/reset-password")
    public SuccessResponse<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest request)
    {
        authService.resetPassword(request);
        return ResponseBuilder.success(MessageConstants.PASSWORD_RESET_SUCCESS);
    }

}
