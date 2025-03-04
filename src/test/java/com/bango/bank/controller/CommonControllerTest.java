package com.bango.bank.controller;
import static com.bango.bank.util.CommonObjects.getFieldErrors;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class CommonControllerTest {

    @Autowired
    CommonController commonController;

    @Test
    void validator() {
        commonController = new CommonController();
        Map<String, Object> errors = getFieldErrors();
        ResponseEntity<?> result = commonController.validator(new InnerCommonControllerTest(true));
        Assertions.assertEquals(ResponseEntity.badRequest().body(errors), result);
    }

}
