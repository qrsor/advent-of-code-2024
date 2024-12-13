package pl.qrsor.adventofcode2024.day13;

import pl.qrsor.adventofcode2024.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreeRowNumberInput {
    public static List<Integer> extractNumbers(String input) {
        List<Integer> extractedNumbers = new ArrayList<>();

        // Regex pattern to match integers (positive whole numbers)
        Pattern numberPattern = Pattern.compile("\\b(\\d+)\\b");

        Matcher matcher = numberPattern.matcher(input);

        // Find and add all numbers in each input string
        while (matcher.find()) {
            extractedNumbers.add(Integer.parseInt(matcher.group(1)));
        }

        return extractedNumbers;
    }

    public static List<Equation> extract(String filePath) {
        List<String> strings = Input.readFile(filePath)
                .stream()
                .filter(Predicate.not(String::isBlank))
                .toList();

        int a1 = 0, b1 = 0, c1, a2 = 0, b2 = 0, c2;
        var result = new ArrayList<Equation>();

        for (int i = 0; i < strings.size(); i++) {
            var line = strings.get(i);
            List<Integer> integers = extractNumbers(line);
            if (i % 3 == 0) {
                a1 = integers.get(0);
                a2 = integers.get(1);
            } else if (i % 3 == 1) {
                b1 = integers.get(0);
                b2 = integers.get(1);
            } else {
                c1 = integers.get(0);
                c2 = integers.get(1);

                result.add(new Equation(new int[]{a1, b1}, new int[]{a2, b2}, c1, c2));
            }
        }

        return result;
    }
}
