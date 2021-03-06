package com.osilva.guiabolso.challenge.content_generator;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.model.Transaction;

import java.util.List;

public interface ContentGenerator {

    List<Transaction> generateTransactionList(Integer id, Integer year, Integer month) throws GenerateContentException;
}
