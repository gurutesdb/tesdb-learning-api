package com.tesdb.learning.master.courseprogram.repository;

import com.tesdb.learning.master.courseprogram.entity.CourseProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseProgramRepository extends JpaRepository<CourseProgram,Long>
{
    Optional<CourseProgram> findByUuid(UUID uuid);

    Optional<CourseProgram> findByName(String name);

    boolean existsByName(String name);
}
