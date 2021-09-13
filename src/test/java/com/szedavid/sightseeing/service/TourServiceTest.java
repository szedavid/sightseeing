package com.szedavid.sightseeing.service;

import com.szedavid.sightseeing.dto.FilterDTO;
import com.szedavid.sightseeing.model.Tour;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
class TourServiceTest {

    @Autowired
    TourService tourService;

    @Test
    void findFilteredTourNames() {
        List<Tour> tours = new ArrayList<>();

        var tour = new Tour();
        tour.setName("Test tour 1 - Budapest");
        tours.add(tour);

        tour = new Tour();
        tour.setName("Test tour 2 - Budapest");
        tours.add(tour);

        tour = new Tour();
        tour.setName("Test tour 3");
        tours.add(tour);

        var filtered = tourService.filterTours(tours, "Budapest");
        assertThat(filtered, hasSize(2));
    }

    @Test
    void refresh() {
        FilterDTO filterDTO = new FilterDTO();
        var refreshedCnt = tourService.refresh(filterDTO);
        assertThat(refreshedCnt, greaterThan(0));
    }
}