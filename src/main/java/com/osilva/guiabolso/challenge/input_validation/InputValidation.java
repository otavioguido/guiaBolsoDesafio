package com.osilva.guiabolso.challenge.input_validation;

import com.osilva.guiabolso.challenge.exception.InputValidationException;

public interface InputValidation {
    void validateInput(Integer id, Integer year, Integer month) throws InputValidationException;
}
