package com.tesdb.learning.student.repository;

import com.tesdb.learning.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, Long>
{
    Optional<Student> findByUuid(UUID uuid);

    Optional<Student> findByEmail(String email);

    Optional<Student> findByRegisterNumber(String registerNumber);

    boolean existsByEmail(String email);

    boolean existsByRegisterNumber(String registerNumber);
}
