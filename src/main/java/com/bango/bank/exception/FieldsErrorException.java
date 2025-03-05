package com.bango.bank.exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class FieldsErrorException extends RuntimeException{
    private final List<FieldError> errors;

    public FieldsErrorException(List<FieldError> errors) {
        super("");
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return this.errors;
    }
}
