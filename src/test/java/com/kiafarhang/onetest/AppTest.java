package com.kiafarhang.onetest;

import com.kiafarhang.onetest.App;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class AppTest {
    @Test
    public void writesToFileProperly() throws IOException {

        String inputPath = "src/test/java/com/kiafarhang/onetest/input-test.txt";
        String outputPath = "src/test/java/com/kiafarhang/onetest/output-test.txt";

        App.writeHistogramToFile(inputPath, outputPath);

        String output = new String(Files.readAllBytes(Paths.get(outputPath)), StandardCharsets.UTF_8);

        String expected = "apple | ==== (4)\nbanana | == (2)\n";

        assertEquals(expected, output);

    }

}
