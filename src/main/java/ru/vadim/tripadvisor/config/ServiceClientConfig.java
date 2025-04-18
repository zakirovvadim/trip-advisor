package ru.vadim.tripadvisor.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import ru.vadim.tripadvisor.client.*;

import java.net.http.HttpClient;
import java.util.concurrent.Executors;

@Configuration
public class ServiceClientConfig {

    public static final Logger log = org.slf4j.LoggerFactory.getLogger(ServiceClientConfig.class);

    @Value("${spring.threads.virtual.enabled}")
    private boolean virtualThreadEnabled;

    @Bean
    public AccomodationServiceClient accomodationServiceClient(@Value("${accommodation.service.url}") String accomodationServiceUrl) {
        return new AccomodationServiceClient(buildRestClient(accomodationServiceUrl));
    }

    @Bean
    public EventServiceClient eventServiceClient(@Value("${event.service.url}") String eventServiceUrl) {
        return new EventServiceClient(buildRestClient(eventServiceUrl));
    }

    @Bean
    public WeatherServiceClient weatherServiceClient(@Value("${weather.service.url}") String eventServiceUrl) {
        return new WeatherServiceClient(buildRestClient(eventServiceUrl));
    }

    @Bean
    public TransportationServiceClient transportServiceClient(@Value("${transportation.service.url}") String transportServiceUrl) {
        return new TransportationServiceClient(buildRestClient(transportServiceUrl));
    }

    @Bean
    public LocalRecommendationServiceClient localRecommendationServiceClient(@Value("${local-recommendation.service.url}") String localRecommendationServiceUrl) {
        return new LocalRecommendationServiceClient(buildRestClient(localRecommendationServiceUrl));
    }

    @Bean
    public FlightResrvationServiceClient flightReservationServiceClient(@Value("${flight-reservation.service.url}") String flightReservationServiceUrl) {
        return new FlightResrvationServiceClient(buildRestClient(flightReservationServiceUrl));
    }

    @Bean
    public FlightSearchServiceClient flightSearchServiceClient(@Value("${flight-search.service.url}") String flightSearchServiceUrl) {
        return new FlightSearchServiceClient(buildRestClient(flightSearchServiceUrl));
    }


    private RestClient buildRestClient(String baseUrl) {
        log.info("Building RestClient for url: {}", baseUrl);
        var builder = RestClient.builder()
                .baseUrl(baseUrl);
        if (virtualThreadEnabled) {
            builder = builder.requestFactory(new JdkClientHttpRequestFactory(
                    HttpClient.newBuilder().executor(Executors.newVirtualThreadPerTaskExecutor()).build()
            ));
        }
        return builder.build();
    }
}
