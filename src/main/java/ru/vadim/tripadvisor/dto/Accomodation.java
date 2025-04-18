package ru.vadim.tripadvisor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Accomodation(@JsonProperty("name") String name,
                           @JsonProperty("type") String type,
                           @JsonProperty("price") int price,
                           @JsonProperty("rating") double rating
) {
}
