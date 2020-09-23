package com.osilva.guiabolso.challenge.content_generator;

import com.osilva.guiabolso.challenge.exception.GenerateContentException;
import com.osilva.guiabolso.challenge.model.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ContentGeneratorUnitTest {

    private ContentGeneratorImpl contentGenerator = new ContentGeneratorImpl();

    @Test
    public void generateTransactionList_shouldReturnCorrectly() throws GenerateContentException {
        Assert.assertTrue(!contentGenerator.generateTransactionList(1000, 2000, 1).isEmpty());
    }

    @Test
    public void generateTransaction_shouldReturnTransaction() throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ContentGeneratorImpl.class.getDeclaredMethod("generateTransaction", Integer.class, Integer.class);
        method.setAccessible(true);
        Transaction output = (Transaction) method.invoke(contentGenerator, 1, 2000);
        Assert.assertNotNull(output);
    }

    @Test
    public void addTransactionToList_shouldAddTransactionToList() throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction("a", 1L, 1, false);
        Method method = ContentGeneratorImpl.class.getDeclaredMethod("addTransactionToList", ArrayList.class, Transaction.class);
        method.setAccessible(true);
        method.invoke(contentGenerator,
                transactions, transaction);
        Assert.assertTrue(transactions.contains(transaction));
    }

    @Test
    public void guaranteeDuplicateTransaction_shouldNotCreateDuplicateForMonthNotMultipleOf4() throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("a", 1L, 1, false));
        Method method = ContentGeneratorImpl.class.getDeclaredMethod("guaranteeDuplicateTransaction", ArrayList.class, Integer.class);
        method.setAccessible(true);
        method.invoke(contentGenerator, transactions, 1);
        Assert.assertTrue(transactions.stream().anyMatch(transaction -> !transaction.getDuplicate()));
    }

    @Test
    public void guaranteeDuplicateTransaction_shouldCreateDuplicateForMonthMultipleOf4() throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("a", 1L, 1, false));
        Method method = ContentGeneratorImpl.class.getDeclaredMethod("guaranteeDuplicateTransaction", ArrayList.class, Integer.class);
        method.setAccessible(true);
        method.invoke(contentGenerator, transactions, 4);
        Assert.assertTrue(transactions.stream().anyMatch(transaction -> transaction.getDuplicate()));
    }
}
