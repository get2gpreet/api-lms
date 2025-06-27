package com.lms.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleEnumParseError(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        String message = "Invalid request payload";
        if (cause instanceof InvalidFormatException invalidEx) {
            // Check if it's our enum
            Class<?> targetType = invalidEx.getTargetType();
            if (targetType.isEnum()) {
                Object[] enumConstants = targetType.getEnumConstants();
                message = "Invalid value. Allowed values: ";
                for (Object ec : enumConstants) {
                    message += ec.toString() + ", ";
                }
                // Remove last comma
                message = message.substring(0, message.length() - 2);
            }
        }

        ErrorResponse errorResponse = new ErrorResponse("Validation failed", List.of(message));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception ex) {
        ErrorResponse error = new ErrorResponse("Validation failed", List.of(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorDetails = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse("Validation failed", errorDetails);
        return ResponseEntity.badRequest().body(errorResponse);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleAllExceptions(Exception ex) {
//        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
