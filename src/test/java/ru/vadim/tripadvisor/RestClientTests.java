package ru.vadim.tripadvisor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import ru.vadim.tripadvisor.dto.Accomodation;
import ru.vadim.tripadvisor.dto.FlightReservationRequest;
import ru.vadim.tripadvisor.dto.FlightReservationResponse;
import ru.vadim.tripadvisor.dto.Weather;

import java.time.LocalDate;
import java.util.List;

class RestClientTests {

	public static final Logger log = LoggerFactory.getLogger(RestClientTests.class);

	@Test
	void contextLoads() {
		var client = RestClient.create();
		var resposne = client.get()
				.uri("http://localhost:7070/sec02/weather/LAS")
				.retrieve()
				.body(Weather.class);

		log.info("response: {}", resposne);
	}

	@Test
	void baseUrlTest() {
		var client = RestClient.create()
				.mutate().baseUrl("http://localhost:7070/sec02/weather/")
				.build();

		var resposne = client.get()
				.uri("{airportCode}", "SFO")
				.retrieve()
				.body(Weather.class);

		log.info("response: {}", resposne);
	}

	@Test
	void listResponse() {
		var client = RestClient.create()
				.mutate().baseUrl("http://localhost:7070/sec02/accommodations/")
				.build();

		var resposne = client.get()
				.uri("{airportCode}", "LAS")
				.retrieve()
				.body(new ParameterizedTypeReference<List<Accomodation>>() {
				});

		log.info("response: {}", resposne);
	}

	@Test
	void postRequest() {
		var client = RestClient.create()
				.mutate().baseUrl("http://localhost:7070/sec03/flight/reserve")
				.build();

		var request = new FlightReservationRequest("ATL", "SFO", "UA789", LocalDate.now());

		var resposne = client.post()
				.body(request)
//				.header("token", "value") example of header
				.retrieve()
				.body(FlightReservationResponse.class);

		log.info("response: {}", resposne);
	}


}
