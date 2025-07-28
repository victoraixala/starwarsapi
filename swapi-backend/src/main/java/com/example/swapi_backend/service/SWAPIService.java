package com.example.swapi_backend.service;

import com.example.swapi_backend.model.Person;
import com.example.swapi_backend.model.Planet;
import com.example.swapi_backend.util.ReflectionUtil;
import com.example.swapi_backend.util.SWAPIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SWAPIService {

    @Value("${swapi.base-url}")
    private String baseUrl;

    List<Person> people;
    List<Planet> planets;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Person> getPeople(String search, String sortBy, String direction) {
        String peopleApiUrl = baseUrl + "/people/";
        log.info("Fetching people from: {}", peopleApiUrl);
        SWAPIResponse<Person> response = new SWAPIResponse<>(Person.class, restTemplate, peopleApiUrl, search);
        if (people == null || people.isEmpty()) people = response.fetchAll();
        return people.stream()
                .filter(p -> p.getName().toLowerCase().contains(search.toLowerCase()))
                .sorted(getComparator(sortBy, direction))
                .collect(Collectors.toList());
    }

    public List<Planet> getPlanets(String search, String sortBy, String direction) {
        String planetsApiUrl = baseUrl + "/planets/";
        log.info("Fetching planets from: {}", planetsApiUrl);
        SWAPIResponse<Planet> response = new SWAPIResponse<>(Planet.class, restTemplate, planetsApiUrl, search);
        if (planets == null || planets.isEmpty()) planets = response.fetchAll();
        return planets.stream()
                .filter(p -> p.getName().toLowerCase().contains(search.toLowerCase()))
                .sorted(getComparator(sortBy, direction))
                .collect(Collectors.toList());
    }

    private <T> Comparator<T> getComparator(String sortBy, String direction) {
        Comparator<T> comparator;

        if ("created".equals(sortBy)) {
            comparator = Comparator.comparing(o -> {
                String dateStr = (String) ReflectionUtil.getFieldValue(o, sortBy);
                return LocalDateTime.parse(dateStr.replace("Z", ""));
            });
        } else {
            comparator = Comparator.comparing(o -> {
                String value = (String) ReflectionUtil.getFieldValue(o, sortBy);
                return value.toLowerCase();
            });
        }

        return "desc".equalsIgnoreCase(direction) ? comparator.reversed() : comparator;
    }
}
