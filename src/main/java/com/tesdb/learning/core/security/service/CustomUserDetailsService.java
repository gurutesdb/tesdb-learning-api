package com.tesdb.learning.core.security.service;

import com.tesdb.learning.admin.entity.Admin;
import com.tesdb.learning.admin.repository.AdminRepository;
import com.tesdb.learning.core.enums.UserType;
import com.tesdb.learning.core.security.model.CustomUserDetails;
import com.tesdb.learning.student.entity.Student;
import com.tesdb.learning.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
    private final StudentRepository studentRepository;

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Student student = studentRepository.findByEmail(email).orElse(null);

        if (student != null)
        {

            String displayName = student.getFirstName();

            if (student.getLastName() != null &&  !student.getLastName().isBlank())
            {
                displayName += " " + student.getLastName();
            }

            return CustomUserDetails.builder()
                    .id(student.getId())
                    .uuid(student.getUuid())
                    .email(student.getEmail())
                    .password(student.getPassword())
                    .displayName(displayName)
                    .profileImage(student.getProfileImage())
                    .userType(UserType.STUDENT)
                    .authorities(List.of(new SimpleGrantedAuthority("ROLE_STUDENT")))
                    .enabled(student.getIsActive())
                    .emailVerified(student.getEmailVerified())
                    .build();
        }

        Admin admin = adminRepository.findByEmail(email).orElse(null);

        if (admin != null) {

            return CustomUserDetails.builder()
                    .id(admin.getId())
                    .uuid(admin.getUuid())
                    .email(admin.getEmail())
                    .password(admin.getPassword())
                    .displayName(admin.getName())
                    .profileImage(null)
                    .userType(UserType.ADMIN)
                    .authorities(List.of(new SimpleGrantedAuthority(admin.getRole().getName())))
                    .enabled(admin.getIsActive())
                    .build();
        }

        throw new UsernameNotFoundException("Invalid email or password.");
    }
}
