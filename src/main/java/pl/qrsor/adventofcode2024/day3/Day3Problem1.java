package pl.qrsor.adventofcode2024.day3;

import java.util.List;
import java.util.regex.Pattern;


public class Day3Problem1 {
    public int validMultiplications(List<String> lists) {
        var pattern = Pattern.compile("mul\\((\\d{1,3},\\d{1,3})\\)");
        return lists.stream().map(line -> {
            var matcher = pattern.matcher(line);
            var result = 0;
            while (matcher.find()) {
                String[] numbers = matcher.group(1).split(",");
                var left = Integer.valueOf(numbers[0]);
                var right = Integer.valueOf(numbers[1]);
                result += left * right;
            }
            return result;
        }).reduce(Integer::sum).orElse(0);
    }

    public int solve(String filePath) {
        return validMultiplications(StringListInput.readInput(filePath));
    }

    public static void main(String[] args) {
        System.out.println("Result " + new Day3Problem1().solve("day3-problem1-input.csv"));
    }
}
