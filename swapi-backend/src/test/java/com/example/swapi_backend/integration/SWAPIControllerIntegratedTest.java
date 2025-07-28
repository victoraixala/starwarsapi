package com.example.swapi_backend.integration;

import com.example.swapi_backend.model.Person;
import com.example.swapi_backend.util.SWAPIResponse;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8089)
public class SWAPIControllerIntegratedTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("swapi.base-url", () -> "http://localhost:8089/");
    }

    @BeforeEach
    void setupMock() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/people/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                {
                  "count": 1,
                  "results": [
                    {
                      "name": "Luke Skywalker",
                      "height": "172",
                      "mass": "77",
                      "url": "http://localhost:8089/people/1"
                    }
                  ]
                }
            """)));


        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/people/1"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                        {
                            "result": {
                                "properties": {
                                    "name": "Luke Skywalker",
                                    "height": "172",
                                    "mass": "77"
                                }
                            }
                        }
                        """)));
    }

    @Test
    void testPeopleEndpointWithMockedSWAPI_andTypedResponse() {
        ParameterizedTypeReference<SWAPIResponse<Person>> typeRef =
                new ParameterizedTypeReference<>() {};

        ResponseEntity<SWAPIResponse<Person>> response =
                restTemplate.exchange("/api/people", HttpMethod.GET, null, typeRef);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        SWAPIResponse<Person> body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getCount());

        List<Person> people = body.getResult();
        assertNotNull(people);
        assertFalse(people.isEmpty());

        Person luke = people.get(0);
        assertEquals("Luke Skywalker", luke.getName());
        assertEquals("172", luke.getHeight());
        assertEquals("77", luke.getMass());
    }

}
