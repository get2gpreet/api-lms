package com.lms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.modal.InputModal;
import com.lms.modal.UserRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LmsController.class)
@ExtendWith(SpringExtension.class)
class LmsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private InputService inputService;


    @Test
    @SneakyThrows
    void testSayHello() {
        mockMvc.perform(get("/api/v1/hello").contentType(MediaType.APPLICATION_JSON)
                        .content("\"Gurpreet\""))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    @SneakyThrows
    void testInputValues() {
        Map<String, Object> stringBooleanMap = new HashMap<>();
        when(inputService.inputValuesCheck(any(InputModal.class))).thenReturn(stringBooleanMap);

        mockMvc.perform(post("/api/v1/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("{}"));
    }

    @Test
    @SneakyThrows
    void testCreateUser() {
        UserRequest userRequest = UserRequest.builder()
                .name("John Doe")
                .age(25)
                .email("john@example.com")
                .build();

        //when(inputService.createUser(any(UserRequest.class))).then((Answer<?>) userRequest);

        mockMvc.perform(post("/api/v1/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("User created successfully!"));
    }

    @Test
    void testCreateUser_whenInvalidInput_thenReturns400() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .name("")
                .age(25)
                .email("jinvalid-email")
                .build();

        mockMvc.perform(post("/api/v1/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.details").isArray());
//                .andExpect(jsonPath("$.details[?(@ =~ /.*name.*/)]").exists())
//                .andExpect(jsonPath("$.details[?(@ =~ /.*email.*/)]").exists())
//                .andExpect(jsonPath("$.details[?(@ =~ /.*age.*/)]").exists());
    }


}