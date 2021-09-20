package com.szedavid.sightseeing.controller;

import com.szedavid.sightseeing.dto.FilterDTO;
import com.szedavid.sightseeing.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This is the controller to manage all requests reltated to tours.
 */

// In a real project I would use Swagger to make sure the Spring Boot APIs are available in a readable manner
// for frontend developers or external consumers.
@RestController
@RequestMapping(value = "/tours")
public class TourController {
    private final Logger log = LoggerFactory.getLogger(TourController.class);

    private TourService tourService;

    @Autowired
    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }

    /**
     * Endpoint to list tour names.
     * Can be filtered by names containing a given text. All tour names will be listed if not set.
     *
     * @param filter The "filter" query parameter to use to filter tours by name.
     * @return JSON object containing the array of (filtered) tour names. Empty array if none is found.
     */
    @GetMapping(value = "")
    public List<String> getTours(
            @RequestParam("filter") Optional<String> filter
    ) {
        log.debug("REQUEST: /tours - filter: {}", filter.orElse("null"));
        return tourService.findFilteredTourNames(filter);
    }

    /**
     * Endpoint to refresh the tour data in the database from the remote webservice.
     * The names of the tours to be refreshed can be set by the request body's JSON object's "filter" property.
     * If the "filter" is null all tours will be refreshed.
     *
     * @param filterDTO JSON object containing the "filter" for the tour names.
     */

    // In a real project I would use pagination
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public void refreshTours(
            @RequestBody FilterDTO filterDTO
    ) {
        log.debug("REQUEST: /tours/refresh - filter: {}", filterDTO.filter);
        tourService.refresh(filterDTO);
    }
}