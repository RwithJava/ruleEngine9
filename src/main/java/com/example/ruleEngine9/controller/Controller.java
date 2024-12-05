package com.example.ruleEngine9.controller;

import com.example.ruleEngine9.model.GreetingRequest;
import com.example.ruleEngine9.model.OrderDiscount;
import com.example.ruleEngine9.model.OrderRequest;
import com.example.ruleEngine9.service.GreetingService;
import com.example.ruleEngine9.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class Controller {

    private final GreetingService greetingService;
    private final OrderService orderService;

    public Controller(GreetingService greetingService, OrderService orderService) {
        this.greetingService = greetingService;
        this.orderService = orderService;
    }

    @PostMapping("/validate-greeting")
    public ResponseEntity<List<String>> validateGreeting(@RequestBody GreetingRequest request) {
        final List<String> results = greetingService.validGreetingString(request.getGreetingString(),
                request.getLength());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/validate-order")
    public ResponseEntity<OrderDiscount> validateOrder(@RequestBody OrderRequest orderRequest) {
        OrderDiscount orderDiscount = orderService.validOrderRequest(orderRequest);
        return new ResponseEntity<>(orderDiscount, HttpStatus.OK);
    }
}
