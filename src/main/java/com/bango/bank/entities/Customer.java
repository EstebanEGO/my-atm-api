package com.bango.bank.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "customers")
@CompoundIndex(name = "customer-card-number", def = "{'cards.number': 1}")
public class Customer {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private Double balance;
    private List<Card> cards;
}
