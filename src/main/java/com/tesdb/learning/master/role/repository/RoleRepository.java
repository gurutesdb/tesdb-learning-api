package com.tesdb.learning.master.role.repository;

import com.tesdb.learning.master.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role,Long>
{

    Optional<Role> findByUuid(UUID uuid);

    Optional<Role> findByName(String name);

    boolean existsByName(String name);

}
