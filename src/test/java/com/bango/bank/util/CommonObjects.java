package com.bango.bank.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.bango.bank.dto.CardRequest;
import com.bango.bank.dto.CustomerRequest;
import com.bango.bank.dto.TransactionRequest;
import com.bango.bank.entities.Card;
import com.bango.bank.entities.Customer;
import com.bango.bank.entities.Transaction;

public class CommonObjects {

    public static TransactionRequest getTransactionRequest() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setCustomerId("1");
        transactionRequest.setAmount(100.0);
        transactionRequest.setConcept("Si");
        transactionRequest.setType("retiro");
        return transactionRequest;
    }

    public static Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setCustomerId("1");
        transaction.setAmount(100.0);
        transaction.setConcept("Si");
        transaction.setType("retiro");
        return transaction;
    }

    public static Customer getCustomer() {
        Card card = new Card();
        card.setNumber("1234");
        card.setPin("12");
        card.setActive(true);

        Customer customer = new Customer();
        customer.setId("1");
        customer.setFirstname("Esteban");
        customer.setLastname("Garcia");
        customer.setBalance(100.0);
        customer.setCards(Arrays.asList(card));
        return customer;
    }

    public static CustomerRequest getCustomerRequest() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstname("Esteban");
        customerRequest.setLastname("Garcia");
        return customerRequest;
    }

    public static Map<String, Object> getFieldErrors() {
        Map<String, Object> errors = new HashMap<>();
        errors.put("name", "El campo name requerido");
        return errors;
    }

    public static CardRequest getCardRequest() {
        CardRequest cardRequest = new CardRequest();
        cardRequest.setNumber("1234");
        cardRequest.setPin("12");
        return cardRequest;
    }
}
