package com.szedavid.sightseeing.sightseeing.controller;

import com.szedavid.sightseeing.sightseeing.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam("filter")Optional<String> filter
            ) {
        // todo only names?
            return tourService.findFilteredTours(filter);
    }
}