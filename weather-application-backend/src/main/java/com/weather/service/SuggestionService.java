package com.weather.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.model.CityData;
import com.weather.model.GeoDBResponse;

@Service
public class SuggestionService {

    private final RestTemplate restTemplate;

    @Autowired
    public SuggestionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CityData> getSuggestions(String input) {
        String apiUrl = "https://wft-geo-db.p.rapidapi.com/v1/geo/cities?namePrefix=" + input;

        // Set headers with the RapidAPI key
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "a1a2a6f47cmsh095b9abc7dd2d9ap131378jsn5b2b4b8f4586");

        ResponseEntity<GeoDBResponse> responseEntity = restTemplate.exchange(
            apiUrl,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            GeoDBResponse.class
        );

       System.out.println(responseEntity.getBody());
        List<CityData> suggestions = new ArrayList<>();
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            GeoDBResponse response = responseEntity.getBody();
            //System.out.println(response.getData());
            if (response != null && response.getData() != null) {
                for (CityData cityData : response.getData()) {
                    suggestions.add(cityData);
                  
                    
                }
            }
        }

        return suggestions;
    }
}
