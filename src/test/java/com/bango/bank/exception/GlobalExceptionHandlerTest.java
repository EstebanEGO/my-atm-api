package com.bango.bank.exception;

import static com.bango.bank.util.CommonObjects.getFieldErrors;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bango.bank.controller.InnerCommonController;

class GlobalExceptionHandlerTest {

    GlobalExceptionHandler exceptionHandler;

    @Test
    void handleException() {
        exceptionHandler = new GlobalExceptionHandler();
        Map<String, Object> errors = getFieldErrors();
        InnerCommonController inner = new InnerCommonController(true);
        FieldsErrorException errorException = new FieldsErrorException(inner.getFieldErrors());

        Map<String, Object> errorsException = exceptionHandler.handleException(errorException);

        Assertions.assertEquals(errors, errorsException);
        Assertions.assertTrue(errorsException.size() > 0);
    }
}
