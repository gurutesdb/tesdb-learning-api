package com.tesdb.learning.auth.dto;

import com.tesdb.learning.core.enums.UserType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CurrentUserResponse
{
    private UUID uuid;

    private String email;

    private String displayName;

    private String role;

    private UserType userType;

    private String profileImage;
}
