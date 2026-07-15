package com.tesdb.learning.master.department.entity;

import com.tesdb.learning.core.entity.BaseEntity;
import com.tesdb.learning.master.courseprogram.entity.CourseProgram;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "department", uniqueConstraints = {@UniqueConstraint(columnNames = {"course_program_id", "name"})})
public class Department extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_program_id", nullable = false)
    private CourseProgram courseProgram;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
