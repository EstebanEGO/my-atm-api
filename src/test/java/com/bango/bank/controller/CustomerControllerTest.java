package com.bango.bank.controller;

import static com.bango.bank.util.CommonObjects.getCustomer;
import static com.bango.bank.util.CommonObjects.getCustomerRequest;
import static com.bango.bank.util.CommonObjects.getFieldErrors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.bango.bank.dto.CustomerRequest;
import com.bango.bank.entities.Card;
import com.bango.bank.entities.Customer;
import com.bango.bank.service.CustomerService;

public class CustomerControllerTest {

    CustomerService customerService = Mockito.mock(CustomerService.class);

    @Autowired
    CustomerController customerController = new CustomerController(customerService);

    List<Customer> customers = new ArrayList<>();
    CustomerRequest customerRequest;
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = getCustomer();
        customerRequest = getCustomerRequest();
        customers.add(customer);
        Mockito.when(customerService.findAll()).thenReturn(customers);

        Mockito.when(customerService.save(customerRequest)).thenReturn(customer.getCards().get(0));
    }

    @Test
    void index() {
        ResponseEntity<List<Customer>> result = customerController.index();
        Assertions.assertTrue(result.getStatusCode().value() == 200);
        Assertions.assertEquals(ResponseEntity.ok().body(customers), result);
    }

    @Test
    void store() {
        Card card = customer.getCards().get(0);
        ResponseEntity<?> result = customerController.store(customerRequest, new InnerCommonControllerTest(false));

        Assertions.assertEquals(ResponseEntity.ok().body(card), result);
        Assertions.assertTrue(result.getStatusCode().value() == 200);
    }

    @Test
    void storeWithFieldErrors() {
        Map<String, Object> errors = getFieldErrors();
        ResponseEntity<?> result = customerController.store(customerRequest, new InnerCommonControllerTest(true));

        Assertions.assertEquals(ResponseEntity.badRequest().body(errors), result);
        Assertions.assertTrue(result.getStatusCode().value() == 400);
    }
}
