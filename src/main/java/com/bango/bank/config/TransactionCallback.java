package com.bango.bank.config;

import java.util.Date;

import org.bson.Document;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveCallback;
import org.springframework.stereotype.Component;

import com.bango.bank.entities.Transaction;

@Order(1)
@Component
public class TransactionCallback implements BeforeConvertCallback<Transaction> {

    @SuppressWarnings("null")
    @Override
    public Transaction onBeforeConvert(Transaction transaction, String collection) {
        transaction.setCreatedAt(new Date());
        return transaction;
    }
}
