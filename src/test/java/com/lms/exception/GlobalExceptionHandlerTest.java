package com.lms.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.InputService;
import com.lms.LmsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(LmsController.class)
@Import(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InputService inputService;

    @Test
    void whenMissingValue_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/global"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.details[0]").value("Value must not be empty"));
    }

    @Test
    void whenValidValue_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/v1/global").param("value", "hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Received: hello"));
    }

}