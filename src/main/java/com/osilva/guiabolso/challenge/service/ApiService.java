package com.osilva.guiabolso.challenge.service;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.transaction.Transaction;

import java.util.List;

public interface ApiService {

    List<Transaction> getTransaction(Integer id, Integer year, Integer month) throws GenerateContentException;
}
