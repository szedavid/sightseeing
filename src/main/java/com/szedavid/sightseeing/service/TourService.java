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

/**
 * Tour related logic.
 */
@Service
public class TourService {

    private TourRepository tourRepository;
    private PocketguideClient pocketguideClient;

    @Autowired
    public void setTourRepository(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Autowired
    public void setPocketguideClient(PocketguideClient pocketguideClient) {
        this.pocketguideClient = pocketguideClient;
    }

    /**
     * Finds the tour names containing the filter string.
     * Uses case-insensitive search.
     * @param filter The text to look for in tour names
     * @return The list of the tours found
     */
    public String findFilteredTourNames(Optional<String> filter) {
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

    /**
     * Refreshes the tours form the remote server.
     * If the filter value is null all tours will be refreshed
     * else only the ones which contain the filter text.
     * Filtering is case-insensitive.
     * @param filterDTO
     */
    public void refresh(FilterDTO filterDTO) {
        var receivedData = pocketguideClient.getTours();
        var tours = receivedData.getTours();
        if (filterDTO.filter != null) {
            tours = filterTours(tours, filterDTO.filter);
        }
        tourRepository.saveAll(tours);
    }

    private ArrayList<Tour> filterTours(List<Tour> tours, String filter) {
        var filteredTours = new ArrayList<>(tours);
        CollectionUtils.filter(filteredTours, tour -> (((Tour) tour).getName().toUpperCase().contains(filter.toUpperCase())));
        return filteredTours;
    }
}
