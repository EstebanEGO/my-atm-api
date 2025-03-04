package com.bango.bank.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bango.bank.dto.CustomerRequest;
import com.bango.bank.dto.TransactionRequest;
import com.bango.bank.entities.Card;
import com.bango.bank.entities.Customer;
import com.bango.bank.entities.Transaction;
import com.bango.bank.repository.CustomerRepository;
import com.bango.bank.util.Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Card save(CustomerRequest customerRequest) {
        String number = Util.generateNumber(1000, 9999);
        String pin = Util.generateNumber(10, 99);
        Card card = new Card(number, pin, true);
        Customer customer = Customer.builder()
                .firstname(customerRequest.getFirstname())
                .lastname(customerRequest.getLastname())
                .balance(0.0)
                .build();
        List<Card> cards = Arrays.asList(card);
        customer.setCards(cards);
        customerRepository.save(customer);
        return card;
    }

    @Override
    public Optional<Customer> findByCardsByNumber(String number) {
        return customerRepository.findByCardsByNumber(number);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public void updateBalance(Customer customer, TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.save(transactionRequest);
        Double amount = transaction.getAmount();
        customer.setBalance(customer.getBalance() + amount);
        customerRepository.save(customer);
    }
}
