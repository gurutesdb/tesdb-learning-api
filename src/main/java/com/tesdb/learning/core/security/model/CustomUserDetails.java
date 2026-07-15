package com.tesdb.learning.core.security.model;

import com.tesdb.learning.core.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CustomUserDetails implements UserDetails
{

    private Long id;

    private UUID uuid;

    private String email;

    private String password;

    private String displayName;

    private String profileImage;

    private UserType userType;

    private Collection<? extends GrantedAuthority> authorities;

    private boolean enabled;

    private boolean emailVerified;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
