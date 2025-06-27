package com.lms;

import com.lms.modal.InputModal;
import com.lms.modal.UserRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InputService {


    public Map<String, Boolean> inputValuesCheck(InputModal input) {
        Map<String, Boolean> map = new HashMap<>();
        input.getWords().forEach(word -> map.put(word, isStringPlam(word)));
        return map;
    }

    private Boolean isStringPlam(String word) {
//        if (word.length() > 1) {
//            throw new IllegalArgumentException("Value parameter is missing or empty!");
//        }
        StringBuilder stringBuilder = new StringBuilder();
        return word.contentEquals(stringBuilder.append(word).reverse());
    }

    public UserRequest createUser(UserRequest userRequest) {
        System.out.println("User created successfully!" + userRequest);
        return userRequest;
    }
}
