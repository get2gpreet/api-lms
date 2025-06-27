package com.lms.modal;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class InputModal {
    private List<String> words;

    private TypeOfValidation typeOfValidation;
}

