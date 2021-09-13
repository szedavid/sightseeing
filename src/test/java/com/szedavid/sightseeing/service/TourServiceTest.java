package com.szedavid.sightseeing.service;

import com.szedavid.sightseeing.model.Tour;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
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

    // todo fix
    // This test fails with "maven verify" using JaCoCo but not with native IntelliJ testing.
    // According to the log the received tour list coming from the remote webservice contains some invalid characters.
    // com.fasterxml.jackson.core.JsonParseException: Invalid UTF-8 middle byte 0x63&#10; at [Source: (ByteArrayInputStream); line: 1, column: 5625]"
//    @Test
//    void refresh() {
//        FilterDTO filterDTO = new FilterDTO();
//        var refreshedCnt = tourService.refresh(filterDTO);
//        assertThat(refreshedCnt, greaterThan(0));
//    }
}