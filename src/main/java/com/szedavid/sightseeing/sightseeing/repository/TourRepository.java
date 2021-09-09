package com.szedavid.sightseeing.sightseeing.repository;

import com.szedavid.sightseeing.sightseeing.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "tours", path = "tours")    // todo think  -  auth?!
@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByNameContainingIgnoreCase(String namePart);  // todo IgnoreCase
}
