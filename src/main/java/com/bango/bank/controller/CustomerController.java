package com.bango.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bango.bank.dto.CustomerRequest;
import com.bango.bank.entities.Card;
import com.bango.bank.entities.Customer;
import com.bango.bank.exception.FieldsErrorException;
import com.bango.bank.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> index() {
        return ResponseEntity.ok().body(customerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Card> store(@Valid @RequestBody CustomerRequest customer, BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldsErrorException(result.getFieldErrors());
        }
        return ResponseEntity.ok().body(customerService.save(customer));
    }
}
