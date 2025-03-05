package com.bango.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bango.bank.dto.CardRequest;
import com.bango.bank.dto.CustomerResponse;
import com.bango.bank.dto.TransactionRequest;
import com.bango.bank.entities.Card;
import com.bango.bank.entities.Customer;
import com.bango.bank.entities.Transaction;
import com.bango.bank.exception.FieldsErrorException;
import com.bango.bank.service.CustomerService;
import com.bango.bank.service.TransactionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/atm")
public class ATMController {

    private final CustomerService customerService;
    private final TransactionService transactionService;

    @PostMapping("/validate/card")
    public ResponseEntity<CustomerResponse> validateCard(@Valid @RequestBody CardRequest cardRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldsErrorException(result.getFieldErrors());
        }

        Optional<Customer> customerOptional = customerService.findByCardsByNumber(cardRequest.getNumber());
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Optional<Card> card = customer.getCards().stream().filter(c -> c.getPin().equals(cardRequest.getPin()))
                    .findFirst();
            if (card.isPresent()) {

                return ResponseEntity.ok(CustomerResponse.builder()
                        .id(customer.getId())
                        .balance(customer.getBalance())
                        .firstname(customer.getFirstname())
                        .lastname(customer.getLastname()).build());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/customer/{customerId}/transaction")
    public ResponseEntity<String> transaction(@PathVariable String customerId,
            @RequestBody TransactionRequest transactionRequest) {
        Optional<Customer> customerOptional = customerService.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getBalance() + transactionRequest.getAmount() >= 0) {
                transactionRequest.setCustomerId(customerId);
                customerService.updateBalance(customerOptional.get(), transactionRequest);
                return ResponseEntity.ok("Transacción realizada con exito");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{customerId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(transactionService.finAllByCustomerId(customerId));
    }
}
