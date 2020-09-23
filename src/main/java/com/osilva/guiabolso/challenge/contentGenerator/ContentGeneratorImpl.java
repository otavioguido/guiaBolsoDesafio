package com.osilva.guiabolso.challenge.contentGenerator;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.model.Transaction;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ContentGeneratorImpl implements ContentGenerator {

    private static final int minTransactions = 1;
    private static final int maxTransactions = 12;
    private static final int minDescriptionSize = 10;
    private static final int maxDescriptionSize = 60;
    private static final int maxValue = 9999999;
    private static final int leftLimit = 97; // letter 'a'
    private static final int rightLimit = 122; // letter 'z'

    private Random random = new Random();

    @Override
    public List<Transaction> generateTransactionList(Integer id, Integer year, Integer month) throws GenerateContentException {
        generateAndSetSeed(id, year, month);
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (int index = 0; index < numberOfTransactions(); index++) {
            addTransactionToList(transactions, generateTransaction(month, year));
        }

        guaranteeDuplicateTransaction(transactions, month);

        return transactions;
    }

    private Transaction generateTransaction(Integer month, Integer year) throws GenerateContentException {
        return new Transaction(
                generateRandomDescription(),
                generateRandomDate(month, year),
                generateRandomValue(),
                false);
    }

    private void addTransactionToList(ArrayList<Transaction> transactions, Transaction transaction) {
        if (transactions.contains(transaction)) {
            transaction.setDuplicate(true);
        }
        transactions.add(transaction);
    }

    private void guaranteeDuplicateTransaction(ArrayList<Transaction> transactions, Integer month) throws GenerateContentException {
        if ((month % 4) == 0) {
            Transaction transaction = transactions.get(random
                    .ints(0, transactions.size())
                    .findFirst()
                    .orElseThrow(() -> new GenerateContentException("Failed to get value from transaction list")));
            addTransactionToList(transactions, transaction);
        }
    }

    private void generateAndSetSeed(Integer id, Integer year, Integer month) {
        random.setSeed(id + year + month);
    }

    private String generateRandomDescription() throws GenerateContentException {
        return random
                .ints(leftLimit, rightLimit + 1)
                .limit(random
                        .ints(minDescriptionSize, maxDescriptionSize + 1)
                        .findFirst()
                        .orElseThrow(() -> new GenerateContentException("Failed to generate random description")))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private Integer generateRandomValue() throws GenerateContentException {
        return random
                .ints(-maxValue, maxValue + 1)
                .findFirst()
                .orElseThrow(() -> new GenerateContentException("Failed to generate random value"));
    }

    private Long generateRandomDate(Integer month, Integer year) throws GenerateContentException {
        LocalDate start = LocalDate.of(year, Month.values()[month-1], 1); // day 1
        LocalDate end = LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth());
        LocalDate result = LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay()));
        return Timestamp.valueOf(result.atStartOfDay()).getTime();
    }

    private Integer numberOfTransactions() throws GenerateContentException {
        return random
                .ints(minTransactions, maxTransactions)
                .findFirst()
                .orElseThrow(() -> new GenerateContentException(
                        "Failed to generate random number of transactions"));
    }
}
