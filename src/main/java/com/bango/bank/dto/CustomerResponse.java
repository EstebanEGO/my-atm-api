package com.bango.bank.dto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerResponse {

    private String id;
    private String firstname;
    private String lastname;
    private Double balance;
}
