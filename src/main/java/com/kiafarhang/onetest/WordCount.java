package com.kiafarhang.onetest;

public class WordCount {
    public static String stripPunctuation(String string) {
        return string.replaceAll("[\\p{Punct}&&[^'-]]", "");
    }
}