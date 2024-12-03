package pl.qrsor.adventofcode2024.day3;

import java.util.List;
import java.util.regex.Pattern;


public class Day3Problem2 {
    public int validMultiplications(List<String> lists) {
        var pattern = Pattern.compile("mul\\((\\d{1,3},\\d{1,3})\\)|(don't\\(\\))|(do\\(\\))");
        var sumEnabled = true;
        boolean seen = false;
        Integer acc = null;
        for (String list : lists) {
            var matcher = pattern.matcher(list);
            var result = 0;

            while (matcher.find()) {
                String wholeGroup = matcher.group(0);
                if (sumEnabled && wholeGroup.startsWith("mul")) {
                    var numbers = matcher.group(1).split(",");
                    var left = Integer.valueOf(numbers[0]);
                    var right = Integer.valueOf(numbers[1]);
                    result += left * right;
                } else if (wholeGroup.startsWith("don")) {
                    System.out.println("Disabling sum");
                    sumEnabled = false;
                } else if (wholeGroup.startsWith("do")) {
                    System.out.println("Enabling sum");
                    sumEnabled = true;
                }


            }
            Integer apply = result;
            if (!seen) {
                seen = true;
                acc = apply;
            } else {
                acc = acc + apply;
            }
        }
        return seen ? acc : 0;
    }

    public int solve(String filePath) {
        return validMultiplications(StringListInput.readInput(filePath));
    }

    public static void main(String[] args) {
        System.out.println("Result " + new Day3Problem2().solve("day3-problem1-input.csv"));
    }
}
