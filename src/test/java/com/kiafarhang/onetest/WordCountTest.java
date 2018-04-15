package com.kiafarhang.onetest;

import com.kiafarhang.onetest.WordCount;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

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

    @Test
    public void createsOneWordMap() {
        String string = "A";
        Map<String, Integer> wordCount = WordCount.createWordCountMap(string);
        assertEquals(1, wordCount.size(), 0);
        assertTrue(wordCount.containsKey("a"));
        assertEquals(1, wordCount.get("a"), 0);
    }

    @Test
    public void createsMultiWordMap() {
        String string = "Apple banana coconut";
        Map<String, Integer> wordCount = WordCount.createWordCountMap(string);
        assertEquals(3, wordCount.size(), 0);

        assertTrue(wordCount.containsKey("apple"));
        assertEquals(1, wordCount.get("apple"), 0);

        assertTrue(wordCount.containsKey("banana"));
        assertEquals(1, wordCount.get("banana"), 0);

        assertTrue(wordCount.containsKey("coconut"));
        assertEquals(1, wordCount.get("coconut"), 0);
    }

    @Test
    public void createsMultiWordMapWithDuplicateWords() {
        String string = "Apple banana coconut apple";
        Map<String, Integer> wordCount = WordCount.createWordCountMap(string);
        assertEquals(3, wordCount.size(), 0);

        assertTrue(wordCount.containsKey("apple"));
        assertEquals(2, wordCount.get("apple"), 0);

        assertTrue(wordCount.containsKey("banana"));
        assertEquals(1, wordCount.get("banana"), 0);

        assertTrue(wordCount.containsKey("coconut"));
        assertEquals(1, wordCount.get("coconut"), 0);
    }

    @Test
    public void handlesPunctuation() {
        String string = "Apple! Banana coconut apple?";
        Map<String, Integer> wordCount = WordCount.createWordCountMap(string);
        assertEquals(3, wordCount.size(), 0);

        assertTrue(wordCount.containsKey("apple"));
        assertEquals(2, wordCount.get("apple"), 0);

        assertTrue(wordCount.containsKey("banana"));
        assertEquals(1, wordCount.get("banana"), 0);

        assertTrue(wordCount.containsKey("coconut"));
        assertEquals(1, wordCount.get("coconut"), 0);
    }

    @Test
    public void respectsApostrophesAndHyphens() {
        String string = "Don't, isn't, well-formed.";
        Map<String, Integer> wordCount = WordCount.createWordCountMap(string);
        assertEquals(3, wordCount.size(), 0);

        assertTrue(wordCount.containsKey("don't"));
        assertEquals(1, wordCount.get("don't"), 0);

        assertTrue(wordCount.containsKey("isn't"));
        assertEquals(1, wordCount.get("isn't"), 0);

        assertTrue(wordCount.containsKey("well-formed"));
        assertEquals(1, wordCount.get("well-formed"), 0);
    }

    @Test
    public void handlesWhitespace() {
        String string = "Hello!\n     Hello World.";
        Map<String, Integer> wordCount = WordCount.createWordCountMap(string);
        assertEquals(2, wordCount.size(), 0);

        assertTrue(wordCount.containsKey("hello"));
        assertEquals(2, wordCount.get("hello"), 0);

        assertTrue(wordCount.containsKey("world"));
        assertEquals(1, wordCount.get("world"), 0);
    }
}