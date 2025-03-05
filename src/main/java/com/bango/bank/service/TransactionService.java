package com.bango.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bango.bank.dto.TransactionRequest;
import com.bango.bank.entities.Transaction;
import com.bango.bank.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> finAllByCustomerId(String customerId) {
        return transactionRepository.finAllByCustomerId(customerId);
    }

    public Transaction save(TransactionRequest transactionRequest) {
        Transaction transaction = Transaction.builder()
                .customerId(transactionRequest.getCustomerId())
                .amount(transactionRequest.getAmount())
                .concept(transactionRequest.getConcept())
                .type(transactionRequest.getType())
                .build();
        return transactionRepository.save(transaction);
    }   
}
