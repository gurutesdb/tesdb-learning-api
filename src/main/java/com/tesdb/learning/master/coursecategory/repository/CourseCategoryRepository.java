package com.tesdb.learning.master.coursecategory.repository;

import com.tesdb.learning.master.coursecategory.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long>
{
    Optional<CourseCategory> findByUuid(UUID uuid);

    Optional<CourseCategory> findByName(String name);
}
