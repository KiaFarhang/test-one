package com.kiafarhang.onetest;

import com.kiafarhang.onetest.WordCount;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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

    @Test
    public void sortsMapsByIntValue() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("Zebra", 20);
        map.put("Elephant", 5);
        map.put("Apple", 1);
        map.put("Stingray", 45);

        Map<String, Integer> sortedMap = WordCount.sortMapByIntegerValueHighestFirst(map);

        // We need to prove that the map is sorted. Since this method is supposed to return a map with guaranteed
        // highest-to-smallest-value insertion order, we can iterate over the resulting map, add items to a list
        // and make sure they were added in the order we expect.

        List<String> results = new ArrayList<String>();

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            results.add(entry.getKey());
        }

        assertEquals(4, results.size(), 0);
        assertEquals("Stingray", results.get(0));
        assertEquals("Zebra", results.get(1));
        assertEquals("Elephant", results.get(2));
        assertEquals("Apple", results.get(3));

    }
}