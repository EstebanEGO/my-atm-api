package com.bango.bank.util;

import java.util.Random;

public class Util {
    public static String generateNumber(int low, int high) {
        Random random = new Random();
        return  (random.nextInt(high - low) + low) + "";
    }
}
