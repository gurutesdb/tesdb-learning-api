package com.tesdb.learning.auth.dto;

import com.tesdb.learning.core.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.UUID;

@Getter
@Builder
public class UserDto
{
    private UUID uuid;

    private String displayName;

    private String email;

    private String role;

    private UserType userType;

    private String profileImage;
}
