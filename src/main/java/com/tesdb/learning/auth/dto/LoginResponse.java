package com.tesdb.learning.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse
{
    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private Long expiresIn;

    private UserDto user;

}
