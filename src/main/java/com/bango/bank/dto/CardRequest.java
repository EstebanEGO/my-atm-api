package com.bango.bank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    @NotBlank
    @Min(value = 16)
    private String number;

    @NotBlank
    @Min(value = 4)
    private String pin;
}
