package com.weather.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.model.CityData;
import com.weather.service.SuggestionService;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping("/api")
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }
    @PostMapping("/suggestions")
    public String postmethod() {
    	return "GHe";
    }

    @GetMapping("/suggestions/{id}")
    public ResponseEntity<List<CityData>> getLocationSuggestions(@PathVariable(value = "id") String input) throws Exception {
    	//System.out.println("suggestions");
        List<CityData> suggestions = suggestionService.getSuggestions(input);
        System.out.println(suggestions);
        return ResponseEntity.ok(suggestions);
    }
}

