package com.osilva.guiabolso.challenge.contentGenerator;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.transaction.Transaction;

import java.util.List;
import java.util.Random;

public interface ContentGenerator {

    Random random = new Random();

    List<Transaction> generateTransactionList(Integer id, Integer year, Integer month) throws GenerateContentException;
}
