package de.telran.urlshortener.controller;

import de.telran.urlshortener.dto.statistics.TopBindingStatisticsResponse;
import de.telran.urlshortener.dto.statistics.UserStatisticsResponse;
import de.telran.urlshortener.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//Аннотация @RestController в Spring Framework - это специализированная версия аннотации
// @Controller, которая упрощает создание RESTful веб-сервисов.
// Она сочетает в себе функциональность аннотаций @Controller и @ResponseBody,
// предоставляя удобный способ создания REST API.
@RestController
@RequestMapping(value = "/api/statistics")
@RequiredArgsConstructor//automatically generate a constructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserStatisticsResponse> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.getByUser(userId));
    }

    @GetMapping("/current")
    public ResponseEntity<UserStatisticsResponse> getByCurrentUser() {
        return ResponseEntity.ok(statisticsService.getByCurrentUser());
    }

    @GetMapping("/top/{top}")
    public ResponseEntity<TopBindingStatisticsResponse> getTopStatistics(@PathVariable int top) {
        TopBindingStatisticsResponse topBindingStatistics = statisticsService.getBindingTop(top);
        return ResponseEntity.ok(topBindingStatistics);
    }

    @GetMapping
    public ResponseEntity<List<UserStatisticsResponse>> getAll() {
        return ResponseEntity.ok(statisticsService.getAll());
    }
}
