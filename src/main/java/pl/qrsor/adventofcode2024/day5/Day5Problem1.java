package pl.qrsor.adventofcode2024.day5;

import java.util.List;
import java.util.stream.Collectors;

public class Day5Problem1 {

    public int middlePageNumbers(RulesAndUpdates rulesAndUpdates) {

        var leftGroupedRules = rulesAndUpdates.rules().stream().collect(Collectors.groupingBy(Rule::smaller, Collectors.mapping(Rule::greater, Collectors.toSet())));
        var rightGroupedRules = rulesAndUpdates.rules().stream().collect(Collectors.groupingBy(Rule::greater, Collectors.mapping(Rule::smaller, Collectors.toSet())));

        var result = 0;

        for (List<Integer> update : rulesAndUpdates.updates()) {
            var isValid = true;
            for (int i = 0; i < update.size() - 1; i++) {
                var leftItem = update.get(i);
                var rightItem = update.get(i + 1);
                if (leftGroupedRules.containsKey(leftItem) && leftGroupedRules.get(leftItem).contains(rightItem)) {
                } else if (rightGroupedRules.containsKey(rightItem) && rightGroupedRules.get(rightItem).contains(leftItem)) {

                } else {
                    isValid = false;
                    break;
                }
            }
            if(isValid) {
                result += update.get(update.size() / 2);
            }
        }

        return result;
    }

    public int solve(String filePath) {
        return middlePageNumbers(RulesAndUpdatesInput.readInput(filePath));
    }

    public static void main(String[] args) {
        System.out.println("Result " + new Day5Problem1().solve("day5-problem1-input.csv"));
    }
}
