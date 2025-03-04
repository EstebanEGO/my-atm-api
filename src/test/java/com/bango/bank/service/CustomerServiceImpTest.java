package com.bango.bank.service;

import static com.bango.bank.util.CommonObjects.getCustomer;
import static com.bango.bank.util.CommonObjects.getCustomerRequest;
import static com.bango.bank.util.CommonObjects.getTransaction;
import static com.bango.bank.util.CommonObjects.getTransactionRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.bango.bank.dto.TransactionRequest;
import com.bango.bank.entities.Card;
import com.bango.bank.entities.Customer;
import com.bango.bank.entities.Transaction;
import com.bango.bank.repository.CustomerRepository;

public class CustomerServiceImpTest {

    CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

    TransactionService transactionService = Mockito.mock(TransactionService.class);

    @Autowired
    CustomerServiceImp customerServiceImp = new CustomerServiceImp(customerRepository, transactionService);

    Customer customer;
    TransactionRequest transactionRequest;
    Transaction transaction;



    @BeforeEach
    void setUp() {
        customer = getCustomer();
        transactionRequest = getTransactionRequest();
        transaction = getTransaction();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        Mockito.when(customerRepository.findByCardsByNumber("1234")).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.findById("1")).thenReturn(Optional.of(customer));
        Mockito.when(transactionService.save(transactionRequest)).thenReturn(transaction);
    }

    @Test
    void findAll() {
        List<Customer> customers = customerServiceImp.findAll();
        Assertions.assertEquals(customer, customers.get(0));
    }

    @Test 
    void findByCardsByNumber() {
        Optional<Customer> customerOptional = customerServiceImp.findByCardsByNumber("1234");
        Assertions.assertTrue(customerOptional.isPresent());
        Assertions.assertEquals(customer, customerOptional.get());
    }

    @Test
    void findById() {
        Optional<Customer> customerOptional = customerServiceImp.findById("1");
        Assertions.assertTrue(customerOptional.isPresent());
        Assertions.assertEquals(customer, customerOptional.get());
    }

    @Test
    void save() {
        Card card = customerServiceImp.save(getCustomerRequest());

        Assertions.assertTrue(card.getNumber().length() == 4);
        Assertions.assertTrue(card.getPin().length() == 2);
    }

    @Test
    void updateBalance() {
        customerServiceImp.updateBalance(customer, transactionRequest);
    }
}
