package com.bango.bank.service;

import static com.bango.bank.util.CommonObjects.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

class CustomerServiceImpTest {

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

        Assertions.assertEquals(4, card.getNumber().length());
        Assertions.assertEquals(2, card.getPin().length());
    }

    @Test
    void updateBalance() {
        customerServiceImp.updateBalance(customer, transactionRequest);

        verify(transactionService, times(1)).save(transactionRequest);
        verify(customerRepository, times(1)).save(customer);
    }
}
