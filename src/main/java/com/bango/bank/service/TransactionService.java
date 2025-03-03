package com.bango.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bango.bank.dto.TransactionRequest;
import com.bango.bank.entities.Customer;
import com.bango.bank.entities.Transaction;
import com.bango.bank.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction save(TransactionRequest transactionRequest, Customer customer) {
        Transaction transaction = Transaction.builder()
                .customerId(customer.getId())
                .amount(transactionRequest.getAmount())
                .concept(transactionRequest.getConcept())
                .type(transactionRequest.getType())
                .createdAt(new Date())
                .build();
        return transactionRepository.save(transaction);
    }

    public List<Transaction> finAllByCustomerId(String customerId) {
        return transactionRepository.finAllByCustomerId(customerId);
    }
}
