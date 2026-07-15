package com.tesdb.learning.master.city.repository;

import com.tesdb.learning.master.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, Long>
{
    Optional<City> findByUuid(UUID uuid);

    boolean existsByNameAndState(String name, String state);
}
