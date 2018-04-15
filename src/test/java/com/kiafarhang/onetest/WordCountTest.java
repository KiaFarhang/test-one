package com.kiafarhang.onetest;

import com.kiafarhang.onetest.WordCount;

import org.junit.Test;
import static org.junit.Assert.*;

public class WordCountTest {
    @Test
    public void stripsPunctuationFromStrings() {
        String original = "Hello! Goodbye.";
        String expected = "Hello Goodbye";
        assertEquals(expected, WordCount.stripPunctuation(original));
    }

    @Test
    public void retainsApostrophes() {
        String original = "We don't want to lose apostrophes.";
        String expected = "We don't want to lose apostrophes";
        assertEquals(expected, WordCount.stripPunctuation(original));
    }

    @Test
    public void retainsHyphens() {
        String original = "We don't want to lose hyphens in words like well-oiled, either.";
        String expected = "We don't want to lose hyphens in words like well-oiled either";
        assertEquals(expected, WordCount.stripPunctuation(original));
    }
}