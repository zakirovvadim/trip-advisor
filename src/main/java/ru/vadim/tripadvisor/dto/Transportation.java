package ru.vadim.tripadvisor.dto;

import java.util.List;

public record Transportation(List<CarRental> carRentals,
                             List<PublicTransportation> publicTransportations) {
}
