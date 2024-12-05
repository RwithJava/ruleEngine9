package com.example.ruleEngine9.controller;

import com.example.ruleEngine9.model.GreetingRequest;
import com.example.ruleEngine9.service.GreetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /*
     * { "greetingString": "Hello World", "length": 11 }
     */
    @PostMapping("/validate-greeting")
    public ResponseEntity<List<String>> validateGreeting(@RequestBody GreetingRequest request) {
        final List<String> results = greetingService.validGreetingString(request.getGreetingString(),
                request.getLength());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
