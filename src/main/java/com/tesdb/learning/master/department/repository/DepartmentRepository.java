package com.tesdb.learning.master.department.repository;

import com.tesdb.learning.master.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, Long>
{
    Optional<Department> findByUuid(UUID uuid);

    List<Department> findByCourseProgramId(Long courseProgramId);

    Optional<Department> findByUuidAndCourseProgramId(UUID uuid, Long courseProgramId);
}
