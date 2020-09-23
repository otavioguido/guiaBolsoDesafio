package com.osilva.guiabolso.challenge.inputValidation;

import com.osilva.guiabolso.challenge.exception.InputValidationException;

public interface InputValidation {
    void validateInput(Integer id, Integer year, Integer month) throws InputValidationException;
}
