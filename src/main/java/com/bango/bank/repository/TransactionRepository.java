package com.bango.bank.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bango.bank.entities.Transaction;

@Repository
public interface TransactionRepository  extends MongoRepository<Transaction, String> {
    @Query(value = "{'customerId': '?0'}", sort = "{ 'createdAt': -1 }")
    List<Transaction> finAllByCustomerId(String curtomerId);
}
