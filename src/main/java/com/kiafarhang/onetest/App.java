package com.kiafarhang.onetest;

import com.kiafarhang.onetest.WordCount;
import com.kiafarhang.onetest.Histogram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // In case someone runs the default gradle 'run' task without passing arguments,
        // we use the default 'input.txt' and 'output.txt' files for the program.

        // However, we have the ability to customize the files we read from and write to if we so choose.
        if (args.length < 2) {
            writeHistogramToFile("input.txt", "output.txt");
        } else {
            writeHistogramToFile(args[0], args[1]);
        }
    }

    public static void writeHistogramToFile(String inputPath, String outputPath) {
        try {
            String input = new String(Files.readAllBytes(Paths.get(inputPath)), StandardCharsets.UTF_8);
            Map<String, Integer> wordCount = new WordCount(input).getWordCount();
            Map<String, Integer> sortedWordCount = WordCount.sortMapByIntegerValueHighestFirst(wordCount);

            List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(
                    sortedWordCount.entrySet());

            StringBuilder stringBuilder = new StringBuilder();

            for (Map.Entry<String, Integer> entry : wordList) {
                stringBuilder.append(Histogram.generateHistogramLine(entry.getKey(), entry.getValue()) + "\n");
            }

            String output = stringBuilder.toString();

            try {
                Files.write(Paths.get(outputPath), output.getBytes(StandardCharsets.UTF_8));
            } catch (IOException exception) {
                throw new Error(exception.toString());
            }

        } catch (IOException exception) {
            throw new Error(exception.toString());
        }
    }
}
