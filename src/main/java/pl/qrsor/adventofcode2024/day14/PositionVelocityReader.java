package pl.qrsor.adventofcode2024.day14;

import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Input;
import pl.qrsor.adventofcode2024.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionVelocityReader {

    public static List<Integer> extractNumbers(String input) {
        List<Integer> extractedNumbers = new ArrayList<>();

        // Regex pattern to match integers (positive whole numbers)
        Pattern numberPattern = Pattern.compile("(-?\\d+)");

        Matcher matcher = numberPattern.matcher(input);

        // Find and add all numbers in each input string
        while (matcher.find()) {
            extractedNumbers.add(Integer.parseInt(matcher.group(1)));
        }

        return extractedNumbers;
    }

    public static List<TeleportingRobot> extract(String filePath, int bathRow, int bathCol) {
        return Input.readFile(filePath).stream().map(line -> {
            var numbers = extractNumbers(line);

            return new TeleportingRobot(new Position(numbers.get(1), numbers.get(0)), new Velocity(numbers.get(3), numbers.get(2)), new Dimensions(bathRow, bathCol));
        }).toList();
    }
}
