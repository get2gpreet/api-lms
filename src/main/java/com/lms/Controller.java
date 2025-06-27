package com.lms;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class Controller {

    InputService inputService;

    @PostMapping("/test")
    public ResponseEntity<?> testInputValues(@RequestBody InputModal input) {
        Map<String, Boolean> stringBooleanMap = inputService.inputValuesCheck(input);
        return ResponseEntity.ok(stringBooleanMap);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello, World!");
    }
}
