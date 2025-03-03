package com.bango.bank.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bango.bank.entities.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Query("{'cards.number': '?0', 'cards.active': true}")
    public Optional<Customer> findByCardsByNumber(String number);
}