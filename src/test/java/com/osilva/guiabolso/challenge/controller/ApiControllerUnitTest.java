package com.osilva.guiabolso.challenge.controller;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.exception.InputValidationException;
import com.osilva.guiabolso.challenge.service.ApiService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApiControllerUnitTest {

    private ApiController apiController = new ApiController();

    @MockBean
    private ApiService apiService;

    @Test
    public void correctInput_CorrectOutput() throws GenerateContentException, InputValidationException {
        when(apiService.getTransaction(1000, 2000, 1)).thenReturn(anyList());
        apiController.getTransaction(1000, 2000, 1);
    }
}
