package com.bango.bank.service;

import java.util.List;
import java.util.Optional;

import com.bango.bank.dto.CustomerRequest;
import com.bango.bank.dto.TransactionRequest;
import com.bango.bank.entities.Card;
import com.bango.bank.entities.Customer;

public interface CustomerService {
    List<Customer> findAll();

    Card save(CustomerRequest customer);

    Optional<Customer> findByCardsByNumber(String number);

    Optional<Customer> findById(String id);

    void updateBalance(Customer customer, TransactionRequest transactionRequest);
}
