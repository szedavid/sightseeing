package com.szedavid.sightseeing.sightseeing.repository;

import com.szedavid.sightseeing.sightseeing.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "tours", path = "tours")    // todo think  -  auth?!
public interface ITourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByNameContainingIgnoreCase(String namePart);  // todo IgnoreCase
}
