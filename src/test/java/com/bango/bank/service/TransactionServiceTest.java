package com.bango.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.bango.bank.dto.TransactionRequest;
import com.bango.bank.entities.Transaction;
import com.bango.bank.repository.TransactionRepository;
import static com.bango.bank.util.CommonObjects.*;

public class TransactionServiceTest {

    TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);

    @Autowired
    TransactionService transactionService = new TransactionService(transactionRepository);

    @BeforeEach
    void setUp() {
        Transaction transaction = getTransaction();
        List<Transaction> list = new ArrayList<>();
        list.add(transaction);
        Mockito.when(transactionRepository.finAllByCustomerId("1")).thenReturn(list);
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);
    }

    @Test
    void finAllByCustomerId() {
        List<Transaction> list = transactionService.finAllByCustomerId("1");
        Assertions.assertNotNull(list);
        Assertions.assertEquals("Si", list.get(0).getConcept());
        Assertions.assertEquals(100.0, list.get(0).getAmount());
    }

    @Test
    void save() {
        TransactionRequest transactionRequest = getTransactionRequest();

        Transaction transaction = transactionService.save(transactionRequest);
        System.out.println(transaction);
        Assertions.assertEquals(transactionRequest.getCustomerId(), transaction.getCustomerId());
        Assertions.assertEquals("Si", transaction.getConcept());
    }
}
