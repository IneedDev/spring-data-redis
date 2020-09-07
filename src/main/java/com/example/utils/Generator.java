package com.example.utils;

import java.util.Random;

public class Generator {

    public static String phoneNumberGenerator() {
        Random rand = new Random();
        int num = rand.nextInt(900000000) + 100000000;
        return Integer.toString(num);
    }
}
