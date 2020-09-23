package com.osilva.guiabolso.challenge;

import com.osilva.guiabolso.challenge.controller.ApiController;
import com.osilva.guiabolso.challenge.exception.InputValidationException;
import com.osilva.guiabolso.challenge.service.ApiServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiServiceImpl apiService;

    @Test
    public void correctInputShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/2000/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void invalidLowerIdShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/999/transacoes/2000/1").contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InputValidationException))
                .andExpect(result -> assertEquals("Id 999 is invalid. It must be between 1.000 and 100.000.000",
                        result.getResolvedException().getMessage()));
    }

    @Test
    public void invalidUpperIdShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/100000001/transacoes/2000/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Id 100000001 is invalid. It must be between 1.000 and 100.000.000"));
    }

    @Test
    public void invalidLowerYearShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/1969/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Year 1969 is invalid. It must be between 1970 and 2020"));
    }

    @Test
    public void invalidUpperYearShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/2021/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Year 2021 is invalid. It must be between 1970 and 2020"));
    }

    @Test
    public void invalidLowerMonthShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/2000/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Month 0 is invalid. It must be between 1 and 12"));
    }

    @Test
    public void invalidUpperMonthShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/2000/13"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Month 13 is invalid. It must be between 1 and 12"));
    }

}
