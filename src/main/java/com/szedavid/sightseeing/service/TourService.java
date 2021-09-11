package com.szedavid.sightseeing.service;

import com.szedavid.sightseeing.client.PocketguideClient;
import com.szedavid.sightseeing.dto.FilterDTO;
import com.szedavid.sightseeing.model.Tour;
import com.szedavid.sightseeing.repository.TourRepository;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<String> names = new ArrayList<>();
        tours.forEach((tour -> names.add(tour.getName())));

        return JSONArray.toJSONString(names);
    }

    public void refresh(FilterDTO filterDTO) {
        var receivedData = pocketguideClient.getTours();
        var tours = receivedData.getTours();
        if(filterDTO.filter != null) {
            tours = filterTours(tours, filterDTO.filter);
        }
        tourRepository.saveAll(tours);
    }

    private ArrayList<Tour> filterTours(List<Tour> tours, String filter) {
        var filteredTours = new ArrayList<>(tours);
        CollectionUtils.filter(filteredTours, tour -> (((Tour)tour).getName().toUpperCase().contains(filter.toUpperCase())));
        return filteredTours;
    }
}
