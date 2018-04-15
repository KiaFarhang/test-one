package com.kiafarhang.onetest;

import java.lang.StringBuilder;

public class Histogram {
    public static String generateHistogramLine(String word, int number) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(word);
        stringBuilder.append(" | ");
        for (int i = 0; i < number; i++) {
            stringBuilder.append("=");
        }
        stringBuilder.append(" ");
        stringBuilder.append("(" + number + ")");

        return stringBuilder.toString();
    }
}