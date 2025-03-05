package com.bango.bank.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilTest {

    @Test
    void generateNumber() {
        Util util = new Util();
        int low = 1;
        int high = 2;
        String result = util.generateNumber(low, high);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("1", result);
    }
}
