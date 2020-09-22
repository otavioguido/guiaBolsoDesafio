package com.osilva.guiabolso.challenge.transaction.transactionFactory;

import com.osilva.guiabolso.challenge.contentGenerator.ContentGenerator;
import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionFactoryImpl implements TransactionFactory {

    @Autowired
    private ContentGenerator contentGenerator;

    @Override
    public List<Transaction> getTransaction(Integer id, Integer year, Integer month) throws GenerateContentException {
        return contentGenerator.generateTransactionList(id, year, month);
    }
}
