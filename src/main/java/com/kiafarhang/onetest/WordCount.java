package com.kiafarhang.onetest;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class WordCount {
    private Map<String, Integer> wordCount;

    public WordCount(String string) {
        this.wordCount = createWordCountMap(string);
    }

    public Map<String, Integer> getWordCount() {
        return this.wordCount;
    }

    public static String stripPunctuation(String string) {
        // We keep apostrophes and hyphens, as they can form words
        return string.replaceAll("[\\p{Punct}&&[^'-]]", "");
    }

    public static Map<String, Integer> createWordCountMap(String string) {
        Map<String, Integer> wordCountMap = new HashMap<String, Integer>();

        String punctuationStripped = stripPunctuation(string);

        String[] splitOnWhiteSpace = punctuationStripped.split("\\s");

        for (String word : splitOnWhiteSpace) {
            // Normalize the word
            word = word.trim().toLowerCase();

            // At this point, we're still counting the empty string "" as a word. So we only move further
            // if the word contains at least one character.

            if (word.length() > 0) {
                // This will be null if the word was absent
                Integer wordsCurrentValue = wordCountMap.putIfAbsent(word, 1);

                // So if it's NOT null, it equals whatever the current count for the given word is, and we should update it.
                if (wordsCurrentValue != null) {
                    wordCountMap.replace(word, wordsCurrentValue + 1);
                }

            }

        }

        return wordCountMap;

    }
}