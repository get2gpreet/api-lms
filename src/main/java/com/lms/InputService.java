package com.lms;

import com.lms.modal.InputModal;
import com.lms.modal.TypeOfValidation;
import com.lms.modal.UserRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InputService {


    public Map<String, Object> inputValuesCheck(InputModal input) throws Exception {
        Map<String, Object> map = new HashMap<>();
        switch (input.getTypeOfValidation()) {
            case TypeOfValidation.palindrome -> input.getWords().forEach(word -> map.put(word, isStringPalindrome(word)));
            case TypeOfValidation.uppercase  -> input.getWords().forEach(word -> map.put(word, word.toUpperCase()));

            default -> throw new Exception("Invalid Input: provide typeOfValidation");
        }
        return map;
    }


    private Boolean isStringPalindrome(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        return word.contentEquals(stringBuilder.append(word).reverse());
    }

    public UserRequest createUser(UserRequest userRequest) {
        System.out.println("User created successfully!" + userRequest);
        return userRequest;
    }
}
