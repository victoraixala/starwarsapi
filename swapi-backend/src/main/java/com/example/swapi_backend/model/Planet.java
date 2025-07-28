package com.example.swapi_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet {
    @JsonProperty("name")
    private String name;
    @JsonProperty("climate")
    private String climate;
    @JsonProperty("created")
    private LocalDateTime created;
    @JsonProperty("diameter")
    private String diameter;
    @JsonProperty("edited")
    private LocalDateTime edited;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("gravity")
    private String gravity;
    @JsonProperty("orbital_period")
    private String orbitalPeriod;
    @JsonProperty("population")
    private String population;
    @JsonProperty("residents")
    private List<String> residents;
    @JsonProperty("rotation_period")
    private String rotationPeriod;
    @JsonProperty("surface_water")
    private String surfaceWater;
    @JsonProperty("terrain")
    private String terrain;
    @JsonProperty("url")
    private String url;
}