# Developer I Programming Challenge #

This is the Developer I Programming Challenge code by [Kia Farhang.](https://github.com/KiaFarhang/ts-portfolio/blob/master/kia-farhang-resume.pdf) This Java program generates a word count histogram from a provided text file and outputs the result to a separate file. Given a file containing the following:

```
Kia wants to work for this company and hopes this company hires him.
```

The resulting file would look like so:

```
this | == (2)
company | == (2)
Kia | = (1)
wants | = (1)
etc. etc.
```

The program allows users to specify the location of the input and output files via command-line arguments.

<hr>

## Running the program ##

This program uses the Gradle build system to manage its tasks. To run the program, clone this repository, enter it and run the following command:

`./gradlew run`

_(Note that you'd use the `./gradlew.bat` file to run the program if you're on a Windows machine)_

You should now have a new `output.txt` file in the project root containing your histogram.

You can pass command-line arguments to Gradle to change the input and output files for the histogram like so:

`./gradlew run -PfileArgs="['input.txt', 'output.txt']"`

Simply change `input.txt` and `output.txt` to file paths of your choosing. Without specifying these command-line
arguments, the program defaults to `input.txt` and `output.txt` in the project's root directory.

<hr>

## Running tests ##

Tests for this program are written with JUnit 4, which comes bundled with Gradle's default Java project configuration. Run them using the test script from the project root:

`./gradlew test`

All tests live in `src/test/java/com/kiafarhang/onetest/` if you'd like to see them.

<hr>

## How it works ##

After converting the input file to a string, the program first creates a HashMap keying every word in the string to the number of times it occurs.

From `src/main/java/com/kiafarhang/onetest/WordCount.java`:

```java
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
```

We ignore whitespace for our purposes, and we also have to avoid punctuation - as `"Hello."` and `"Hello"` probably shouldn't be treated as two separate words. Nor, for that matter, should `"Hello"`and `"hello"` - we normalize all words in the input string by setting them to lowercase.

I chose to allow two punctuation marks: hyphens and apostrophes. My thinking was `"don't"` shouldn't be normalized to `"dont"` and compound words like `"well-written"` are their own words and should be treated as such.

<hr>

Maps aren't inherently sortable, but we need to guarantee the words that occur most frequently will come out of our map first when we iterate over it to create the histogram. We pass our map to a method that uses it to create a LinkedHashMap with guaranteed iteration order based on insertion.

From `src/main/java/com/kiafarhang/onetest/WordCount.java`:

```java
    public static <T> Map<T, Integer> sortMapByIntegerValueHighestFirst(Map<T, Integer> map) {

        List<Map.Entry<T, Integer>> mapAsList = new LinkedList<Map.Entry<T, Integer>>(map.entrySet());

        // We compare b to a to sort by highest number first
        Collections.sort(mapAsList, (a, b) -> b.getValue().compareTo(a.getValue()));

        Map<T, Integer> sortedMap = new LinkedHashMap<>();

        for (Map.Entry<T, Integer> entry : mapAsList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
```

This was the trickiest part of the challenge for me; I wanted something clean (and generic) that I could actually understand. There's a commented-out section of that method with another approach using lambdas in the file; I'd be interested in stepping through it with the team if there's time to learn more about how it works.

<hr>

Once we have a map with guaranteed most-frequent-words-first iteration order, we just need to build a string containing histogram "lines" for each map entry. The program generates a List from that map and passes each item to the following method.

From `src/main/java/com/kiafarhang/onetest/Histogram.java`:

```java
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
```

Once we've got this master string, we just write it to the file at the provided output path and we're done!

For a bit more detail on my decision-making throughout the project, you can check [the commit log](https://github.com/KiaFarhang/test-one/commits/master).

<hr>

## Questions for the team ##

- Are there data structures other than Maps that we could use to solve this problem? If so, which ones and what would that implementation look like?
- Are there better ways to achieve the most-frequent-words-first sorting necessary for this problem?
- I stuck mainly to public, static methods as I find those easier to reason about and test (data in, data out). Is this common in an object-oriented language like Java? What are the drawbacks to this approach? 