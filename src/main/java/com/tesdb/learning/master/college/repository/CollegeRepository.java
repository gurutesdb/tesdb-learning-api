package com.tesdb.learning.master.college.repository;

import com.tesdb.learning.master.college.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CollegeRepository extends JpaRepository<College, Long>
{
    Optional<College> findByUuid(UUID uuid);

    List<College> findByCityId(Long cityId);

    Optional<College> findByUuidAndCityId(UUID uuid, Long cityId);
}
