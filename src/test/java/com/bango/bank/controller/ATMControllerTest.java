package com.bango.bank.controller;

import static com.bango.bank.util.CommonObjects.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.bango.bank.dto.CardRequest;
import com.bango.bank.dto.CustomerResponse;
import com.bango.bank.dto.TransactionRequest;
import com.bango.bank.entities.Customer;
import com.bango.bank.entities.Transaction;
import com.bango.bank.service.CustomerService;
import com.bango.bank.service.TransactionService;

public class ATMControllerTest {

    CustomerService customerService = Mockito.mock(CustomerService.class);
    TransactionService transactionService = Mockito.mock(TransactionService.class);

    ATMController atmController = new ATMController(customerService, transactionService);
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = getCustomer();
        Mockito.when(customerService.findByCardsByNumber("1234")).thenReturn(Optional.of(customer));
        Mockito.when(customerService.findById("1")).thenReturn(Optional.of(customer));
        Mockito.when(transactionService.finAllByCustomerId("1")).thenReturn(Arrays.asList(getTransaction()));
    }

    @Test
    void validateCard() {
        CardRequest cardRequest = getCardRequest();

        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(customer.getId())
                .balance(customer.getBalance())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname()).build();

        ResponseEntity<?> response = atmController.validateCard(cardRequest, new InnerCommonControllerTest(false));

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(customerResponse, response.getBody());
    }

    @Test
    void validateCardWithNumber404() {
        CardRequest cardRequest = getCardRequest();
        cardRequest.setNumber("4321");
        ResponseEntity<?> response = atmController.validateCard(cardRequest, new InnerCommonControllerTest(false));
        Assertions.assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void validateCardWithPin404() {
        CardRequest cardRequest = getCardRequest();
        cardRequest.setPin("21");
        ResponseEntity<?> response = atmController.validateCard(cardRequest, new InnerCommonControllerTest(false));
        Assertions.assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void validateCardWithFieldErrors() {
        Map<String, Object> errors = getFieldErrors();
        CardRequest cardRequest = getCardRequest();
        ResponseEntity<?> response = atmController.validateCard(cardRequest, new InnerCommonControllerTest(true));
        Assertions.assertEquals(400, response.getStatusCode().value());
        Assertions.assertEquals(ResponseEntity.badRequest().body(errors), response);
    }

    @Test
    void transaction() {
        TransactionRequest transactionRequest = getTransactionRequest();
        ResponseEntity<?> response = atmController.transaction("1", transactionRequest);

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(ResponseEntity.ok().body("Transacción realizada con exito"), response);
    }

    @Test
    void transactionWithBadId404() {
        TransactionRequest transactionRequest = getTransactionRequest();
        ResponseEntity<?> response = atmController.transaction("2", transactionRequest);

        Assertions.assertEquals(404, response.getStatusCode().value());
        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void transactionWithBadAmount404() {
        TransactionRequest transactionRequest = getTransactionRequest();
        transactionRequest.setAmount(-200.0);
        ResponseEntity<?> response = atmController.transaction("1", transactionRequest);

        Assertions.assertEquals(404, response.getStatusCode().value());
        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void getTransactionsByCustomer() {
        List<Transaction> list = Arrays.asList(getTransaction());

        ResponseEntity<List<Transaction>> response = atmController.getTransactionsByCustomer("1");

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(ResponseEntity.ok().body(list), response);
    }
}
