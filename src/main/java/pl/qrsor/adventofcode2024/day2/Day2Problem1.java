package pl.qrsor.adventofcode2024.day2;

import java.util.List;

public class Day2Problem1 {
    private long countSafeReports(List<List<Integer>> lists) {
        return lists.stream().map(levels -> {
            var shouldBeIncreasing = levels.get(1) - levels.get(0) > 0;
            for (int i = 1; i < levels.size(); i++) {
                var current = levels.get(i);
                var prev = levels.get(i - 1);
                var distance = current - prev;
                var isIncreasing = distance > 0;
                if (Math.abs(distance) < 1 || Math.abs(distance) > 3 || (shouldBeIncreasing != isIncreasing)) {
                    return false;
                }
            }
            return true;
        }).filter(x -> x.equals(Boolean.TRUE)).count();
    }

    public long solve(String filePath) {
        return countSafeReports(RowListInput.readInput(filePath));
    }

    public static void main(String[] args) {
        System.out.println("Result " + new Day2Problem1().solve("day2-problem1-input.csv"));
    }
}
