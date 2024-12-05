package pl.qrsor.adventofcode2024.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day5Problem2 {

    public int middlePageNumbers(RulesAndUpdates rulesAndUpdates) {

        var leftGroupedRules = rulesAndUpdates.rules().stream().collect(Collectors.groupingBy(Rule::smaller, Collectors.mapping(Rule::greater, Collectors.toSet())));
        var rightGroupedRules = rulesAndUpdates.rules().stream().collect(Collectors.groupingBy(Rule::greater, Collectors.mapping(Rule::smaller, Collectors.toSet())));

        var result = 0;

        for (List<Integer> update : rulesAndUpdates.updates()) {

            var originalUpdate = new ArrayList<>(update);

            var wasFixed = false;
            for (int i = 0; i < update.size() - 1; i++) {
                var leftItem = update.get(i);
                var rightItem = update.get(i + 1);
                if (leftGroupedRules.containsKey(leftItem) && leftGroupedRules.get(leftItem).contains(rightItem)) {
                } else if (rightGroupedRules.containsKey(rightItem) && rightGroupedRules.get(rightItem).contains(leftItem)) {

                } else {
                    wasFixed = true;
                    update.set(i, rightItem);
                    update.set(i + 1, leftItem);
                    i = -1;
                }
            }
            if (wasFixed) {
                System.out.println(originalUpdate);
                System.out.println(update);
                int middle = update.size() / 2;
                result += update.get(middle);
            }
        }

        return result;
    }

    public int solve(String filePath) {
        return middlePageNumbers(RulesAndUpdatesInput.readInput(filePath));
    }

    public static void main(String[] args) {
        System.out.println("Result " + new Day5Problem2().solve("day5-problem1-input.csv"));
    }
}
