package de.telran.urlshortener.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public record UrlBindingCreateRequestDto(
        @NotBlank//гарантирует, что поле содержит хотя бы один символ, не являющийся пробелом
        String originalUrl,
        String pathPrefix,
        @FutureOrPresent//Дата или время в будущем относительно текущего времени.
//        Текущая дата или время.
        LocalDate expirationDate
) {
}
