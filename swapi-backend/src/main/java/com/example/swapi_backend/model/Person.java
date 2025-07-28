package com.example.swapi_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    @JsonProperty("name")
    private String name;
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("eye_color")
    private String eyeColor;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("hair_color")
    private String hairColor;
    @JsonProperty("height")
    private String height;
    @JsonProperty("homeworld")
    private String homeworld;
    @JsonProperty("mass")
    private String mass;
    @JsonProperty("skin_color")
    private String skinColor;
    @JsonProperty("created")
    private LocalDateTime created;
    @JsonProperty("edited")
    private LocalDateTime edited;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("species")
    private List<String> species;
    @JsonProperty("starships")
    private List<String> starships;
    @JsonProperty("url")
    private String url;
    @JsonProperty("vehicles")
    private List<String> vehicles;
}
