package com.szedavid.sightseeing.sightseeing.controller;

import com.szedavid.sightseeing.sightseeing.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping("/tours/refresh")
//    public String refresh(
////  todo ?           @Validated RefreshFilter refreshFilter
//            @RequestBody RefreshFilter refreshFilter
//    ) {
//
//    }
}