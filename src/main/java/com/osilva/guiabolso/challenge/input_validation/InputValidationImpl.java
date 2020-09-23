package com.osilva.guiabolso.challenge.input_validation;

import com.osilva.guiabolso.challenge.exception.InputValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InputValidationImpl implements InputValidation {

    private static final int MIN_ID = 1000;
    private static final int MAX_ID = 100000000;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;

    @Override
    public void validateInput(Integer id, Integer year, Integer month) throws InputValidationException {
        validateId(id);
        validateMonth(month);
        validateYear(year);
    }

    private void validateId(Integer id) throws InputValidationException {
        if (id == null || id < MIN_ID || id > MAX_ID) {
            throw new InputValidationException(
                    String.format("Id %d is invalid. It must be between 1.000 and 100.000.000", id));
        }
    }

    private void validateYear(Integer year) throws InputValidationException {
        int currentYear = LocalDate.now().getYear();
        if (year < 1970 || year > currentYear) {
            throw new InputValidationException(
                    String.format("Year %d is invalid. It must be between 1970 and %d.", year, currentYear));
        }
    }

    private void validateMonth(Integer month) throws InputValidationException {
        if (month < MIN_MONTH || month > MAX_MONTH) {
            throw new InputValidationException(
                    String.format("Month %d is invalid. It must be between 1 and 12", month));
        }
    }
}
