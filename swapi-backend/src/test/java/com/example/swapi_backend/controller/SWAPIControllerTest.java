package com.example.swapi_backend.controller;

import com.example.swapi_backend.model.Person;
import com.example.swapi_backend.model.Planet;
import com.example.swapi_backend.service.SWAPIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SWAPIControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SWAPIService service;

    @BeforeEach
    public void setup() {
        SWAPIController controller = new SWAPIController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetPeople() throws Exception {
        Person person = new Person();
        person.setName("Luke Skywalker");
        person.setCreated(LocalDateTime.of(2014, 12, 9, 13, 50, 51));

        when(service.getPeople(anyString(), anyString(), anyString()))
                .thenReturn(List.of(person));

        mockMvc.perform(get("/api/people"))
                .andDo(result -> {
                    String contentType = result.getResponse().getContentType();
                    System.out.println("Returned Content-Type: " + contentType);
                })
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0].name").value("Luke Skywalker"));
    }

    @Test
    public void testGetPlanets() throws Exception {
        Planet planet = new Planet();
        planet.setName("Tatooine");
        planet.setCreated(LocalDateTime.now());

        when(service.getPlanets(anyString(), anyString(), anyString()))
                .thenReturn(List.of(planet));

        mockMvc.perform(get("/api/planets"))
                .andDo(result -> {
                    String contentType = result.getResponse().getContentType();
                    System.out.println("Returned Content-Type: " + contentType);
                })
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0].name").value("Tatooine"));
    }
}
