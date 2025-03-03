package com.bango.bank.entities;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "transactions")
public class Transaction {

    @Id
    private String id;
    private String customerId;
    private Date createdAt;
    private Double amount;
    private String concept;
    private String type;
}
