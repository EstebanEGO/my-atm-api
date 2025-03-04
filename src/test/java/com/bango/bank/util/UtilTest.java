package com.bango.bank.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    void generateNumber() {
        Util u = new Util();
        System.out.println(u);
        int low = 1;
        int high = 2;
        String result = Util.generateNumber(low, high);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("1", result);
    }
}
