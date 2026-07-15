package com.tesdb.learning.student.entity;

import com.tesdb.learning.core.entity.BaseEntity;
import com.tesdb.learning.core.enums.AuthProvider;
import com.tesdb.learning.core.enums.Gender;
import com.tesdb.learning.master.city.entity.City;
import com.tesdb.learning.master.college.entity.College;
import com.tesdb.learning.master.courseprogram.entity.CourseProgram;
import com.tesdb.learning.master.department.entity.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 15)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "register_number", nullable = false, unique = true, length = 50)
    private String registerNumber;

    @Column(nullable = false)
    private Short semester;

    @Column(name = "passing_out_year", nullable = false)
    private Integer passingOutYear;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_program_id", nullable = false)
    private CourseProgram courseProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id", nullable = false)
    private College college;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "resume_file")
    private String resumeFile;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false, length = 20)
    private AuthProvider authProvider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
