package com.example.swapi_backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
public class SWAPIResponse<T> {
    private List<T> result;
    private int count;

    private Class<T> type;
    private RestTemplate restTemplate;
    private String baseUrl;
    private String search;

    public SWAPIResponse() {}

    public SWAPIResponse(Class<T> type, RestTemplate restTemplate, String baseUrl, String search) {
        this.type = type;
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.search = search;
    }

    public List<T> fetchAll() {
        List<T> allItems = new ArrayList<>();
        String url = baseUrl;
        int page = 1;

        log.info("Starting SWAPI fetch for type {}", type.getSimpleName());

        while (true) {
            log.info("Fetching page {} from URL: {}", page, url);

            ResponseEntity<Map> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
            } catch (Exception e) {
                log.error("Exception during SWAPI request to {}", url, e);
                break;
            }

            Map<String, Object> body = response.getBody();
            if (body == null) {
                log.warn("Empty body at page {}", page);
                break;
            }

            List<T> items = extractItems(body);
            allItems.addAll(items);

            Object next = body.get("next");
            if (next == null || next.toString().isEmpty()) {
                log.info("No more pages to fetch. Total pages: {}", page);
                break;
            }

            url = next.toString();
            page++;
        }

        log.info("Finished fetching {} total items of type {}", allItems.size(), type.getSimpleName());
        return allItems;
    }

    private List<T> extractItems(Map<String, Object> body) {
        List<T> parsedItems = new ArrayList<>();

        Object rawList = body.get("results");
        if (!(rawList instanceof List)) {
            log.warn("Expected a List in 'results', got: {}", rawList);
            return parsedItems;
        }

        List<Map<String, Object>> items = (List<Map<String, Object>>) rawList;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        for (Map<String, Object> item : items) {
            String detailUrl = item.get("url").toString();
            try {
                ResponseEntity<Map> detailResp =
                        restTemplate.exchange(detailUrl, HttpMethod.GET, null, Map.class);
                Map<String, Object> detailBody = detailResp.getBody();

                if (detailBody != null && detailBody.get("result") instanceof Map) {
                    Map<String, Object> resultData = (Map<String, Object>) detailBody.get("result");
                    Object detailProps = resultData.get("properties");
                    if (detailProps instanceof Map) {
                        T obj = mapper.convertValue(detailProps, type);
                        parsedItems.add(obj);
                        log.debug("Fetched details from URL: {}", detailUrl);
                    }
                }

            } catch (Exception e) {
                log.error("Error converting item: {}", e.getMessage());
            }
        }

        return parsedItems;
    }
}
