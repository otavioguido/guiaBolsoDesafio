package com.osilva.guiabolso.challenge.controller;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/")
public class ApiController {

    private static final int minId = 1000;
    private static final int maxId = 100000000;
    private static final int minMonth = 1;
    private static final int maxMonth = 12;

    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/{id}/transacoes/{ano}/{mes}")
    public ResponseEntity<?> getTransaction(
            @PathVariable @NotNull @Min(minId) @Max(maxId) Integer id,
            @PathVariable(name = "ano") @NotNull Integer year,
            @PathVariable(name = "mes") @NotNull @Min(minMonth) @Max(maxMonth) Integer month)
    {
        try {
            return new ResponseEntity<>(apiService.getTransaction(id, year, month), HttpStatus.OK);
        } catch (GenerateContentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
