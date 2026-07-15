package com.tesdb.learning.auth.entity;

import com.tesdb.learning.admin.entity.Admin;
import com.tesdb.learning.student.entity.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
public class RefreshToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String token;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private Boolean revoked = false;

    @Column(nullable = false)
    private Boolean expired = false;

    @Column(name = "created_at", nullable =false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist()
    {
        if(uuid == null)
        {
            uuid = UUID.randomUUID();
        }

        if(createdAt == null)
        {
            createdAt = LocalDateTime.now();
        }
    }
}
