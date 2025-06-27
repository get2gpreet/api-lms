package com.lms;

import com.lms.modal.InputModal;
import com.lms.modal.UserRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InputService {


    public Map<String, Boolean> inputValuesCheck(InputModal input) throws Exception {
        Map<String, Boolean> map = new HashMap<>();
        switch (input.getTypeOfValidation()) {
            case  "palindrome" -> invokeStringPalindrome(input,map);
            default -> throw new Exception("Invalid Input: provide typeOfValidation");
        }
        return map;
    }

    private void invokeStringPalindrome(InputModal input, Map<String, Boolean> map) {
        input.getWords().forEach(word -> map.put(word, isStringPlam(word)));
    }

    private Boolean isStringPlam(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        return word.contentEquals(stringBuilder.append(word).reverse());
    }

    public UserRequest createUser(UserRequest userRequest) {
        System.out.println("User created successfully!" + userRequest);
        return userRequest;
    }
}
