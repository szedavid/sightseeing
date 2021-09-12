package com.szedavid.sightseeing.controller;

import com.szedavid.sightseeing.dto.FilterDTO;
import com.szedavid.sightseeing.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/tours")
public class TourController {
    private final Logger log = LoggerFactory.getLogger(TourController.class);

    private TourService tourService;

    @Autowired
    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("")
    public String getTours(
            @RequestParam("filter") Optional<String> filter
    ) {
        log.debug("REQUEST: /tours - filter: {}", filter.orElse("null"));
        return tourService.findFilteredTourNames(filter);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public void refresh(
            @RequestBody FilterDTO filterDTO
    ) {
        log.debug("REQUEST: /tours/refresh - filter: {}", filterDTO.filter);
        tourService.refresh(filterDTO);
    }
}