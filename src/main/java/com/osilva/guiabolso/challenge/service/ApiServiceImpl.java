package com.osilva.guiabolso.challenge.service;

import com.osilva.guiabolso.challenge.contentGenerator.ContentGenerator;
import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ContentGenerator contentGenerator;

    @Override
    public List<Transaction> getTransaction(Integer id, Integer year, Integer month) throws GenerateContentException {
        return contentGenerator.generateTransactionList(id, year, month);
    }
}
