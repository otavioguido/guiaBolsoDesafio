package com.osilva.guiabolso.challenge.controller;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.exception.InputValidationException;
import com.osilva.guiabolso.challenge.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/")
@Slf4j
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/{id}/transacoes/{ano}/{mes}")
    public ResponseEntity<?> getTransaction(
            @PathVariable Integer id,
            @PathVariable(name = "ano") Integer year,
            @PathVariable(name = "mes") Integer month)
    {
        try {
            return new ResponseEntity<>(apiService.getTransaction(id, year, month), HttpStatus.OK);
        } catch (GenerateContentException | InputValidationException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
