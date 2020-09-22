package com.osilva.guiabolso.challenge.controller;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.service.ApiService;
import com.osilva.guiabolso.challenge.transaction.Transaction;
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

import java.util.List;

import static com.osilva.guiabolso.challenge.constants.GlobalVariables.*;

@RestController
@RequestMapping("/")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/{id}/transacoes/{ano}/{mes}")
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
