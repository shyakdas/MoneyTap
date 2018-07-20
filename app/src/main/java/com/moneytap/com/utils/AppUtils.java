package com.moneytap.com.utils;

import android.graphics.Color;

import java.util.Random;

public class AppUtils {

    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
