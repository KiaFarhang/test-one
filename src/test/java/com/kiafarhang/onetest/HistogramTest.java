package com.kiafarhang.onetest;

import com.kiafarhang.onetest.Histogram;

import org.junit.Test;
import static org.junit.Assert.*;

public class HistogramTest {
    @Test
    public void properlyFormatsHistogramLine() {
        String expectedOne = "test | == (2)";
        String resultOne = Histogram.generateHistogramLine("test", 2);
        assertEquals(resultOne, expectedOne);

        String expectedTwo = "frequent | ========== (10)";
        String resultTwo = Histogram.generateHistogramLine("frequent", 10);
        assertEquals(resultTwo, expectedTwo);

        String expectedNoOccurrences = "zero |  (0)";
        String resultNoOccurrences = Histogram.generateHistogramLine("zero", 0);
        assertEquals(resultNoOccurrences, expectedNoOccurrences);
    }
}