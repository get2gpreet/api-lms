package com.lms;

import com.lms.modal.InputModal;
import com.lms.modal.UserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LmsController {

    InputService inputService;

    @PostMapping("/test")
    public ResponseEntity<?> testInputValues(@RequestBody InputModal input) throws Exception {
        Map<String, Object> stringBooleanMap = inputService.inputValuesCheck(input);
        return ResponseEntity.ok(stringBooleanMap);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello, World!");
    }

    @PostMapping("/user/add")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserRequest response= inputService.createUser(userRequest);
        return ResponseEntity.ok("User created successfully!");
    }

    @GetMapping("/global")
    public String test(@RequestParam(required = false) String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value must not be empty");
        }
        return "Received: " + value;
    }
}
