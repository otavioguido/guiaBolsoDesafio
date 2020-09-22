package com.osilva.guiabolso.challenge.contentGenerator;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.osilva.guiabolso.challenge.constants.GlobalVariables.*;

@Component
public class ContentGeneratorImpl implements ContentGenerator {

    @Override
    public List<Transaction> generateTransactionList(Integer id, Integer year, Integer month) throws GenerateContentException {
        generateAndSetSeed(id, year, month);
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (int index = 0; index < numberOfTransactions(); index++) {
            Transaction transaction = generateTransaction(month, year);
            addTransactionToList(transactions, transaction);
        }

        guaranteeDuplicateTransaction(transactions, month);

        return transactions;
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

    private Transaction generateTransaction(Integer month, Integer year) throws GenerateContentException {
        return Transaction
                .builder()
                .date(generateRandomDate(month, year))
                .description(generateRandomDescription())
                .value(generateRandomValue())
                .duplicate(false)
                .build();
    }

    private void addTransactionToList(ArrayList<Transaction> transactions, Transaction transaction) {
        if (transactions.contains(transaction)) {
            transaction.setDuplicate(true);
        }
        transactions.add(transaction);
    }

    private void generateAndSetSeed(Integer id, Integer year, Integer month) {
        random.setSeed(id + year + month + Instant.now().toEpochMilli());
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
