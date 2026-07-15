package com.tesdb.learning.admin.repository;

import com.tesdb.learning.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, Long>
{
    Optional<Admin> findByEmail(String email);

    Optional<Admin> findByUuid(UUID uuid);
}
