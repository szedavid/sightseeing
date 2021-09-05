package com.szedavid.sightseeing.sightseeing.service;

import com.szedavid.sightseeing.sightseeing.entity.Tour;
import com.szedavid.sightseeing.sightseeing.repository.ITourRepository;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    private final ITourRepository tourRepository;

    @Autowired
    public TourService(ITourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public String findFilteredTours(Optional<String> filter) {
        List<Tour> tours;
        if (filter.isPresent()) {
            tours = tourRepository.findByNameContainingIgnoreCase(filter.get());
        } else {
            tours = tourRepository.findAll();
        }

        return JSONArray.toJSONString(tours);
    }
}
