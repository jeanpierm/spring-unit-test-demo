package com.jm.demo.operations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api")
public class OperationsController {

    private final OperationsService operationsService;

    @GetMapping(path = "hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello, World!");
    }

    @GetMapping(path = "factorial")
    public ResponseEntity<Map<String, Object>> factorial(@RequestParam int num) {
        try {
            final int factorial = operationsService.factorial(num);
            final Map<String, Object> body = Map.of("result", factorial);
            return ResponseEntity.ok().body(body);
        } catch (ArithmeticException | IllegalArgumentException e) {
            final Map<String, Object> body = Map.of("message", e.getMessage() != null ? e.getMessage() : "Invalid operation");
            return ResponseEntity.badRequest().body(body);
        }
    }
}