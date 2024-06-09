package de.telran.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Комбинирует три аннотации: @Configuration, @EnableAutoConfiguration и @ComponentScan.
//Используется для обозначения основного класса конфигурации приложения Spring Boot.
@SpringBootApplication
public class UrlShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }

}
