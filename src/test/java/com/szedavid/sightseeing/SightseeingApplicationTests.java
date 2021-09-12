package com.szedavid.sightseeing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SightseeingApplicationTests {

    @Autowired
    private MockMvc mvc;

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
    // for some reason this test fails with "maven verify" but not with intellij testing
    // com.fasterxml.jackson.core.JsonParseException: Invalid UTF-8 middle byte 0x63&#10; at [Source: (ByteArrayInputStream); line: 1, column: 5625]"
//    @Test
//    public void refreshTours() throws Exception {
//        mvc.perform(
//                        MockMvcRequestBuilders.post("/tours/refresh")
//                                .contentType(MediaType.APPLICATION_JSON).content("{\"filter\": null}")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .with(httpBasic("admin","admin12")))
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
    public void getToursNames() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tours")
                        .with(httpBasic("john", "john12"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // should return filtered array
    @Test
    public void getFilteredTourNames() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tours?filter=Pécs rejtett kincsei")
                        .with(httpBasic("john", "john12"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[\"PÃ©cs rejtett kincsei dr. PÃ¡va Zsolt polgÃ¡rmester vezetÃ©sÃ©vel\"]")));
    }

}