package com.lms;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InputService {


    public Map<String, Boolean> inputValuesCheck(InputModal input) {
        System.out.println("input: " + input);
        Map<String, Boolean> map = new HashMap<>();
        input.getWords().forEach(word -> map.put(word, isStringPlam(word)));
        return map;
    }

    private Boolean isStringPlam(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        return word.contentEquals(stringBuilder.append(word).reverse());
    }
}
