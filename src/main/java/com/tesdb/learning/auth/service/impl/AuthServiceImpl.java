package com.tesdb.learning.auth.service.impl;

import com.tesdb.learning.auth.dto.*;
import com.tesdb.learning.auth.entity.PasswordResetToken;
import com.tesdb.learning.auth.entity.RefreshToken;
import com.tesdb.learning.auth.service.AuthService;
import com.tesdb.learning.auth.service.EmailVerificationTokenService;
import com.tesdb.learning.auth.service.PasswordResetTokenService;
import com.tesdb.learning.auth.service.RefreshTokenService;
import com.tesdb.learning.core.constant.JwtConstants;
import com.tesdb.learning.core.constant.MessageConstants;
import com.tesdb.learning.core.exception.EmailNotVerifiedException;
import com.tesdb.learning.core.exception.ResourceNotFoundException;
import com.tesdb.learning.core.properties.SecurityProperties;
import com.tesdb.learning.core.response.ResponseBuilder;
import com.tesdb.learning.core.response.SuccessResponse;
import com.tesdb.learning.core.security.service.CustomUserDetailsService;
import com.tesdb.learning.notification.service.EmailService;
import com.tesdb.learning.student.entity.Student;
import com.tesdb.learning.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tesdb.learning.core.security.jwt.JwtService;
import com.tesdb.learning.core.security.model.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    private final CustomUserDetailsService customUserDetailsService;

    private final EmailVerificationTokenService emailVerificationTokenService;

    private final SecurityProperties securityProperties;

    private final PasswordResetTokenService passwordResetTokenService;

    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Value("${app.frontend.base-url}")
    private String frontendBaseUrl;

    @Override
    public LoginResponse login(LoginRequest request)
    {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        if (securityProperties.isRequireEmailVerification() && !user.isEmailVerified())
        {
            throw new EmailNotVerifiedException("Please verify your email before logging in.");
        }

        String accessToken = jwtService.generateAccessToken(user);

        String refreshToken = jwtService.generateRefreshToken(user);

        refreshTokenService.createRefreshToken(user, refreshToken);

        UserDto userDto = UserDto.builder()
                .uuid(user.getUuid())
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .role(
                        user.getAuthorities()
                                .stream()
                                .findFirst()
                                .orElseThrow()
                                .getAuthority()
                )
                .userType(user.getUserType())
                .profileImage(user.getProfileImage())
                .build();


        log.info("Student '{}' logged in successfully.", user.getEmail());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(JwtConstants.BEARER.trim())
                .expiresIn(jwtService.getAccessTokenExpiration())
                .user(userDto)
                .build();
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request)
    {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());

        String email = jwtService.extractUsername(refreshToken.getToken());

        CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);

        String newAccessToken = jwtService.generateAccessToken(user);

        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .tokenType(JwtConstants.BEARER.trim())
                .expiresIn(jwtService.getAccessTokenExpiration())
                .build();
    }

    @Override
    public CurrentUserResponse getCurrentUser()
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        String role = user.getAuthorities()
                .stream()
                .findFirst()
                .map(a -> a.getAuthority())
                .orElse("");

        return CurrentUserResponse.builder()
                .uuid(user.getUuid())
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .role(role)
                .userType(user.getUserType())
                .profileImage(user.getProfileImage())
                .build();
    }


    @Override
    public void verifyEmail(VerifyEmailRequest request)
    {
        emailVerificationTokenService.verifyToken(request.getToken());
        //log.info("Email verified successfully for '{}'.", request.getEmail());
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request)
    {

        Student student = studentRepository.findByEmail(request.getEmail()).orElseThrow(() ->
        {
            throw new ResourceNotFoundException(MessageConstants.STUDENT_NOT_FOUND);}
        );

        PasswordResetToken passwordResetToken = passwordResetTokenService.createToken(student);

        String resetLink =frontendBaseUrl + "/reset-password?token=" + passwordResetToken.getToken();

        log.info("Password reset requested for '{}'.", student.getEmail());

        String subject = "TESDB Learning - Reset Your Password";

        String body = """
        Hello %s,

        We received a request to reset your password.

        Click the link below to reset your password:

        %s

        This link will expire in 30 minutes.

        If you did not request a password reset, you can safely ignore this email.

        Regards,
        TESDB Learning Team
        """.formatted(student.getFirstName(), resetLink);

        emailService.sendEmail(student.getEmail(), subject, body);

        log.info("Password reset email sent to '{}'.", student.getEmail());
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordRequest request)
    {

        PasswordResetToken passwordResetToken = passwordResetTokenService.validateToken(request.getToken());

        Student student = passwordResetToken.getStudent();

        student.setPassword(passwordEncoder.encode(request.getPassword()));

        studentRepository.save(student);

        log.info("Password successfully reset for '{}'.", student.getEmail());

        passwordResetTokenService.markTokenAsUsed(passwordResetToken);

    }
}
