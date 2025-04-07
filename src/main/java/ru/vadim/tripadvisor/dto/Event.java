package ru.vadim.tripadvisor.dto;

import java.time.LocalDate;

public record Event(String name,
                    String description,
                    LocalDate localDate) {
}
