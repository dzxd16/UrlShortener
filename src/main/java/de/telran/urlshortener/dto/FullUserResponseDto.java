package de.telran.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

public record FullUserResponseDto(
        UserResponseDto userResponseDto,

        @JsonInclude(JsonInclude.Include.NON_NULL)//пустые поля не передаются
        Set<SubscriptionResponseDto> subscriptionResponseDtoSet,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Set<UrlBindingResponseDto> bindingResponseDtoSet) {

}