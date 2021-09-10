package com.szedavid.sightseeing.service;

import com.szedavid.sightseeing.client.PocketguideClient;
import com.szedavid.sightseeing.client.dto.PocketguideDataDTO;
import com.szedavid.sightseeing.dto.RefreshFilterDTO;
import com.szedavid.sightseeing.dto.TourDTO;
import com.szedavid.sightseeing.entity.Tour;
import com.szedavid.sightseeing.repository.TourRepository;

import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final PocketguideClient pocketguideClient;

    // todo másképp
    @Autowired
    public TourService(TourRepository tourRepository, PocketguideClient pocketguideClient) {
        this.tourRepository = tourRepository;
        this.pocketguideClient = pocketguideClient;
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

    public String refresh(RefreshFilterDTO filter) {
        PocketguideDataDTO receivedData = pocketguideClient.getTours();
        List<Tour> tours = receivedData.getTours();
        // todo filter
        return JSONArray.toJSONString(tours);
    }
}