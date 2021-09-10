package com.szedavid.sightseeing;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class SightseeingApplicationTests {

	@Autowired
	private MockMvc mvc;

	// todo in separate files (not only endpoints, and failing endpoints)

	// should throw Unauthorized status
	@Test
	public void refreshToursWithoutAuth() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/tours/refresh").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	// todo should return forbidden status
	@Test
	public void refreshToursWithLowLevelAuth() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/tours/refresh")
						.accept(MediaType.APPLICATION_JSON)
						.with(httpBasic("john","john12")))
				.andExpect(status().isForbidden());
	}

	// todo should return OK (200) status
	@Test
	public void refreshToursWithWrongPwd() throws Exception {
		mvc.perform(
						MockMvcRequestBuilders.post("/tours/refresh")
								.accept(MediaType.APPLICATION_JSON)
								.with(httpBasic("admin","john12")))
				.andExpect(status().isUnauthorized());
	}

	// should return OK (200) status
	@Test
	public void refreshTours() throws Exception {
		mvc.perform(
						MockMvcRequestBuilders.post("/tours/refresh")
								.accept(MediaType.APPLICATION_JSON)
								.with(httpBasic("admin","admin12")))
				.andExpect(status().isOk());
	}

	// todo auth check !
	// should return empty array if no result for filter
	@Test
	public void getNonExistingTours() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/tours?filter=nonExistingName").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}

	// should return filtered array
	@Test
	public void getFilteredTours() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/tours?filter=Pécs rejtett kincsei").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[\"PÃ©cs rejtett kincsei dr. PÃ¡va Zsolt polgÃ¡rmester vezetÃ©sÃ©vel\"]")));
		// todo above coding ?
	}

	// todo others
}