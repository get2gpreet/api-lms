package com.lms;

import com.lms.modal.InputModal;
import com.lms.modal.UserRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InputServiceTest {

    @InjectMocks
    InputService  inputService;

    @Test
    void inputValuesCheck() {
        System.out.println("inputValuesCheck");

        InputModal inputModal = InputModal.builder()
                .words(Arrays.asList("hello", "world", "rotor", "1v1"))
                .build();

        Map<String, Boolean> stringBooleanMap = inputService.inputValuesCheck(inputModal);
        assertEquals(stringBooleanMap.size(),4);
    }

    //createUser
    @Test
    void testCreateUser() {
        UserRequest request = UserRequest.builder()
                .name("John Doe")
                .age(25)
                .email("john@example.com")
                .build();

        UserRequest response = inputService.createUser(request);
        assertNotNull(response.getName());
    }

}