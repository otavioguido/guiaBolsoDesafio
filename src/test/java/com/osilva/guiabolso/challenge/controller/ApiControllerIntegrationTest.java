package com.osilva.guiabolso.challenge.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void correctInputShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/2000/1"))
                .andExpect(status().isOk());
    }

    @Test
    void invalidLowerIdShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/999/transacoes/2000/1").contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Id 999 is invalid. It must be between 1.000 and 100.000.000"));
    }

    @Test
    void invalidUpperIdShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/100000001/transacoes/2000/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Id 100000001 is invalid. It must be between 1.000 and 100.000.000"));
    }

    @Test
    void invalidLowerYearShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/1969/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Year 1969 is invalid. It must be between 1970 and 2020"));
    }

    @Test
    void invalidUpperYearShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/2021/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Year 2021 is invalid. It must be between 1970 and 2020"));
    }

    @Test
    void invalidLowerMonthShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/2000/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Month 0 is invalid. It must be between 1 and 12"));
    }

    @Test
    void invalidUpperMonthShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/1000/transacoes/2000/13"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Month 13 is invalid. It must be between 1 and 12"));
    }

}
