package com.szedavid.sightseeing.controller;

import com.szedavid.sightseeing.model.Tour;
import com.szedavid.sightseeing.service.RoleService;
import com.szedavid.sightseeing.service.TourService;
import com.szedavid.sightseeing.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// In a real project I would use a mock DB for tests. (Mockito)
// If testing speed would be critical I would mock remote webservice connections too.
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TourControllerTest {
    Long testIDs[] = {999999999999999998L, 999999999999999999L};

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    TourService tourService;

    @Autowired
    private MockMvc mvc;

    @BeforeAll
    public void init() {
        roleService.initForDemo();
        userService.initForDemo();

        var tour = new Tour();
        tour.setId(testIDs[0]);
        tour.setName("First test tour");
        tour.setLongDesc("First test tour used for tests");
        tourService.save(tour);

        tour = new Tour();
        tour.setId(testIDs[1]);
        tour.setName("Second test tour");
        tour.setLongDesc("Second test tour used for tests");
        tourService.save(tour);

        System.out.println("DB ready for testing.");
    }

    @AfterAll
    public void clean() {
        tourService.deleteById(testIDs[0]);
        tourService.deleteById(testIDs[1]);

        System.out.println("Test data removed from DB.");
    }

    // REFRESH

    // should return Unauthorized status code
    @Test
    public void refreshToursWithoutAuth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/tours/refresh")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // should return Forbidden status code
    @Test
    public void refreshToursWithLowLevelAuth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/tours/refresh")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(httpBasic("john", "john12")))
                .andExpect(status().isForbidden());
    }

    // should return Unauthorized status code
    @Test
    public void refreshToursWithWrongPwd() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/tours/refresh")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(httpBasic("admin", "john12")))
                .andExpect(status().isUnauthorized());
    }

    // todo fix
    // This test fails with "maven verify" using JaCoCo but not with native IntelliJ testing.
    // According to log the received tour list comong from the remote webservice contains some invalid characters.
    // com.fasterxml.jackson.core.JsonParseException: Invalid UTF-8 middle byte 0x63&#10; at [Source: (ByteArrayInputStream); line: 1, column: 5625]"
//    @Test
//    public void refreshTours() throws Exception {
//        mvc.perform(
//                        MockMvcRequestBuilders.post("/tours/refresh")
//                                .with(csrf())
//                                .contentType(MediaType.APPLICATION_JSON).content("{\"filter\": null}")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .with(httpBasic("admin", "admin12")))
//                .andExpect(status().isOk());
//    }

    // TOURS

    // should return Unauthorized status code
    @Test
    public void getToursWithoutAuth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tours")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // should return empty array when no result for filter
    @Test
    public void getEmptyArrayIfNoFilteredToursFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tours?filter=nonExistingName")
                        .with(httpBasic("john", "john12"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    // should return tour names
    @Test
    public void getTours() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tours")
                        .with(httpBasic("john", "john12"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // should return filtered array
    @Test
    public void getFilteredTourNames() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tours?filter=First test")
                        .with(httpBasic("john", "john12"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[\"First test tour\"]")));
    }

}