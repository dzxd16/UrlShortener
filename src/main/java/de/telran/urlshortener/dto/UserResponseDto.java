package de.telran.urlshortener.dto;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email
) {

}
