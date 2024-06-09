package de.telran.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

public record UrlBindingResponseDto(
        Long id,
        String originalUrl,
        String shortUrl,

        @JsonInclude(JsonInclude.Include.NON_NULL)//пустые поля не передаются
        Long count,
        LocalDate creationDate,
        LocalDate expirationDate
) {
}
