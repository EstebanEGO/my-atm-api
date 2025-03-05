package com.bango.bank.util;

import java.security.SecureRandom;
import java.util.Random;

public class Util {
    Random random = new SecureRandom();

    public Util() {
        //Is empty
    }

    public String generateNumber(int low, int high) {
        return (random.nextInt(high - low) + low) + "";
    }
}
