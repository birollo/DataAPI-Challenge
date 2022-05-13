package com.swisscom.DataAPIChallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swisscom.DataAPIChallenge.controller.MainController;

import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import com.swisscom.DataAPIChallenge.model.Language;
import com.swisscom.DataAPIChallenge.repository.DialogRepository;
import com.swisscom.DataAPIChallenge.service.CustomerService;
import com.swisscom.DataAPIChallenge.service.DialogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@ExtendWith(MockitoExtension.class)
class DataApiChallengeApplicationTests {

	@Mock
	private MainController mainController;



	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(mainController).build();
	}

	@Test
	void dataApiText() throws Exception {
		String customerID = "customerTest1";
		String dialogId = "dialogTest1";
		Map<String, String> payload = new HashMap<>();
		payload.put("text", "test text");
		payload.put("language","EN");
		MockHttpServletResponse response = mvc.perform(
						post("/data/"+ customerID +"/" + dialogId)
								.content(asJsonString(payload))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
						.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		response = mvc.perform(
						post("/data/"+ customerID +"/" +dialogId))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void dataApiConsent() throws Exception {
		String dialogId = "dialogTest1";

		MockHttpServletResponse response = mvc.perform(
						post("/consent/"+ dialogId)
								.content(asJsonString(true))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		response = mvc.perform(
				post("/consent/"+ dialogId)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	void getDataWithCustomer() throws Exception {
		String customerID = "customerTest1";

		MockHttpServletResponse response = mvc.perform(
						get("/data/"+ customerID))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
