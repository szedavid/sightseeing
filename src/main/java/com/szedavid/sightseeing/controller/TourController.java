package com.szedavid.sightseeing.controller;

import com.szedavid.sightseeing.dto.FilterDTO;
import com.szedavid.sightseeing.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class TourController {
    private final TourService tourService;

    // todo autowired set-re (máshol is!)
    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/tours")
    public String getTours(
            @RequestParam("filter") Optional<String> filter
            ) {
        // todo only names?
            return tourService.findFilteredTours(filter);
    }

    @PostMapping("/tours/refresh")
    @ResponseStatus(HttpStatus.OK)
    public void refresh(
            @RequestBody FilterDTO filterDTO
    ) {
//        log.info("Request to create student: {}", student); todo log in other places too
            tourService.refresh(filterDTO);
    }
}