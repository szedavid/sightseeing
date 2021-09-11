package com.szedavid.sightseeing.controller;

import com.szedavid.sightseeing.dto.FilterDTO;
import com.szedavid.sightseeing.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TourController {
    private TourService tourService;

    @Autowired
    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/tours")
    public String getTours(
            @RequestParam("filter") Optional<String> filter
            ) {
            return tourService.findFilteredTourNames(filter);
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