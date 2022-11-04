package com.qa.alcohol;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.alcohol.persistance.domain.CraftBeer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:CraftBeer-schema.sql",
		"classpath:CraftBeer-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CraftBeerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		// JSON
		CraftBeer testCraftBeer = new CraftBeer(null, "Stoutzilla '22: Barrel Aged", "Double Imperial Stout",
				"Unbarred", 11, "Bottle", 750, true, false, 18);
		String testCraftBeerAsJSON = this.mapper.writeValueAsString(testCraftBeer);

		System.out.println("Request Body: " + testCraftBeerAsJSON);

		RequestBuilder req = post("/craftbeer/create").contentType(MediaType.APPLICATION_JSON)
				.content(testCraftBeerAsJSON);

		testCraftBeer.setId(2L);
		String responseBody = this.mapper.writeValueAsString(testCraftBeer);

		System.out.println("Response Body: " + responseBody);

		ResultMatcher checkStatus = status().is(200);
		ResultMatcher checkBody = content().json(responseBody);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGet() throws Exception {
		CraftBeer testCraftBeer = new CraftBeer(1L, "Joosy", "Hazy Pale Ale", "Unbarred", 5.5, "can", 440, true, false,
				4);
		this.mvc.perform(get("/craftbeer/get/1")).andExpect(status().isOk())
				.andExpect(content().json(this.mapper.writeValueAsString(testCraftBeer)));
	}

	@Test
	void testUpdate() throws Exception {
		// JSON
		CraftBeer testCraftBeer = new CraftBeer(null, "Stoutzilla '22: Barrel Aged", "Double Imperial Stout",
				"Unbarred", 11, "Bottle", 750, true, false, 18);
		String testCraftBeerAsJSON = this.mapper.writeValueAsString(testCraftBeer);

		RequestBuilder req = put("/craftbeer/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(testCraftBeerAsJSON);

		testCraftBeer.setId(1L);
		String responseBody = this.mapper.writeValueAsString(testCraftBeer);

		ResultMatcher checkStatus = status().is(200);
		ResultMatcher checkBody = content().json(responseBody);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testDelete() throws Exception {

		this.mvc.perform(delete("/craftbeer/delete/1")).andExpect(status().isOk());

	}

}
