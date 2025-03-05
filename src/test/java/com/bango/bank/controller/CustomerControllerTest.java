package com.bango.bank.controller;

import static com.bango.bank.util.CommonObjects.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.bango.bank.dto.CustomerRequest;
import com.bango.bank.entities.Card;
import com.bango.bank.entities.Customer;
import com.bango.bank.exception.FieldsErrorException;
import com.bango.bank.service.CustomerService;

class CustomerControllerTest {

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
        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(ResponseEntity.ok().body(customers), result);
    }

    @Test
    void store() {
        Card card = customer.getCards().get(0);
        ResponseEntity<?> result = customerController.store(customerRequest, new InnerCommonController(false));

        Assertions.assertEquals(ResponseEntity.ok().body(card), result);
        Assertions.assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void storeWithFieldErrors() {
        InnerCommonController inner = new InnerCommonController(true);
        FieldsErrorException fieldsErrorException = assertThrows(FieldsErrorException.class,
                () -> customerController.store(customerRequest, inner));
        Assertions.assertTrue(fieldsErrorException.getErrors().size() > 0);
        Assertions.assertEquals("name", fieldsErrorException.getErrors().get(0).getField());
    }
}
