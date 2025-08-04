package com.example.swapi_backend.controller;

import com.example.swapi_backend.model.Person;
import com.example.swapi_backend.model.Planet;
import com.example.swapi_backend.service.SWAPIService;
import com.example.swapi_backend.util.SWAPIResponse;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:6969", "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class SWAPIController {

    private final SWAPIService service;

    public SWAPIController(SWAPIService service) {
        this.service = service;
    }

    @GetMapping("/people")
    public ResponseEntity<SWAPIResponse<Person>> getPeople(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        List<Person> people = service.getPeople(search, sortBy, direction);
        SWAPIResponse<Person> response = new SWAPIResponse<>();
        response.setResult(people);
        response.setCount(people.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/planets")
    public ResponseEntity<SWAPIResponse<Planet>> getPlanets(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String direction) {
        List<Planet> planets = service.getPlanets(search, sortBy, direction);
        SWAPIResponse<Planet> response = new SWAPIResponse<>();
        response.setResult(planets);
        response.setCount(planets.size());
        return ResponseEntity.ok(response);
    }
}