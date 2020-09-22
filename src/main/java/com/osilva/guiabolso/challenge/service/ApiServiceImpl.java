package com.osilva.guiabolso.challenge.service;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.transaction.Transaction;
import com.osilva.guiabolso.challenge.transaction.transactionFactory.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private TransactionFactory transactionFactory;

    @Override
    public List<Transaction> getTransaction(Integer id, Integer year, Integer month) throws GenerateContentException {
        return transactionFactory.getTransaction(id, year, month);
    }
}
