package com.kiafarhang.onetest;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

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

    /**
     * Given a Map of objects with Integer values, returns a Map (LinkedHashMap) of the same objects
     * but inserted in the order of highest value first. The LinkedHashMap guarantees that iterating over
     * the returned Map will always give you access to the map entries in the same highest-value-first order.
     */
    public static <T> Map<T, Integer> sortMapByIntegerValueHighestFirst(Map<T, Integer> map) {

        List<Map.Entry<T, Integer>> mapAsList = new LinkedList<Map.Entry<T, Integer>>(map.entrySet());

        // We compare b to a to sort by highest number first
        Collections.sort(mapAsList, (a, b) -> b.getValue().compareTo(a.getValue()));

        Map<T, Integer> sortedMap = new LinkedHashMap<>();

        for (Map.Entry<T, Integer> entry : mapAsList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;

        // The below code also works, but I can't follow all the lambdas (found this online).
        // Would be curious to talk about how this works - it loses me at the toMap() call, as I'm seeing three parameters
        // passed - the getKey and getValue methods and a lambda function that seems to actually create the LinkedHashMap.

        // Map<T, Integer> sortedMap = map.entrySet().stream()
        //         .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
        //                 Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    }
}