package com.osilva.guiabolso.challenge.input_validation;

import com.osilva.guiabolso.challenge.exception.InputValidationException;
import org.junit.Test;

public class InputValidationUnitTest {

    private InputValidationImpl inputValidation = new InputValidationImpl();

    @Test
    public void validateCorrectInput() throws InputValidationException {
        inputValidation.validateInput(1000, 2000, 1);
    }

    @Test(expected = InputValidationException.class)
    public void validateInvalidLowerId() throws InputValidationException {
        inputValidation.validateInput(999, 2000, 1);
    }

    @Test(expected = InputValidationException.class)
    public void validateInvalidUpperId() throws InputValidationException {
        inputValidation.validateInput(100000001, 2000, 1);
    }

    @Test(expected = InputValidationException.class)
    public void validateInvalidLowerYear() throws InputValidationException {
        inputValidation.validateInput(1000, 1969, 1);
    }

    @Test(expected = InputValidationException.class)
    public void validateInvalidUpperYear() throws InputValidationException {
        inputValidation.validateInput(1000, 2021, 1);
    }

    @Test(expected = InputValidationException.class)
    public void validateInvalidLowerMonth() throws InputValidationException {
        inputValidation.validateInput(1000, 2000, 0);
    }

    @Test(expected = InputValidationException.class)
    public void validateInvalidUpperMonth() throws InputValidationException {
        inputValidation.validateInput(1000, 2000, 13);
    }
}
