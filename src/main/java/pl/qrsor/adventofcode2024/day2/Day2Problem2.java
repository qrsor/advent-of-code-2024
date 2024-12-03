package pl.qrsor.adventofcode2024.day2;

import java.util.ArrayList;
import java.util.List;

public class Day2Problem2 {
    private static final boolean ASC = true;
    private static final int NOT_FOUND = -1;
    private static final boolean DESC = false;

    private long countSafeReports(List<List<Integer>> lists) {
        return lists.stream().map(levels -> {
            // check ascending
            var indexViolatingAscending = getViolationIndexOfMonotonicListWithLimitedGap(levels, ASC);
            if (indexViolatingAscending == NOT_FOUND) {
                return true;
            }
            var indexViolatingDescending = getViolationIndexOfMonotonicListWithLimitedGap(levels, DESC);
            if (indexViolatingDescending == NOT_FOUND) {
                return true;
            }

            var listWithoutViolator = new ArrayList<>(levels);
            listWithoutViolator.remove(indexViolatingAscending - 1);
            if (getViolationIndexOfMonotonicListWithLimitedGap(listWithoutViolator, ASC) == NOT_FOUND) {
                return true;
            }

            listWithoutViolator = new ArrayList<>(levels);
            listWithoutViolator.remove(indexViolatingAscending);
            if (getViolationIndexOfMonotonicListWithLimitedGap(listWithoutViolator, ASC) == NOT_FOUND) {
                return true;
            }

            listWithoutViolator = new ArrayList<>(levels);
            listWithoutViolator.remove(indexViolatingDescending - 1);
            if (getViolationIndexOfMonotonicListWithLimitedGap(listWithoutViolator, DESC) == NOT_FOUND) {
                return true;
            }

            listWithoutViolator = new ArrayList<>(levels);
            listWithoutViolator.remove(indexViolatingDescending);
            return getViolationIndexOfMonotonicListWithLimitedGap(listWithoutViolator, DESC) == NOT_FOUND;
        }).filter(x -> x.equals(Boolean.TRUE)).count();
    }

    public static int getViolationIndexOfMonotonicListWithLimitedGap(List<Integer> levels, boolean shouldBeIncreasing) {
        for (int i = 1; i < levels.size(); i++) {
            var current = levels.get(i);
            var prev = levels.get(i - 1);
            var distance = current - prev;
            var isIncreasing = distance > 0;
            if ((Math.abs(distance) < 1 || Math.abs(distance) > 3 || (shouldBeIncreasing != isIncreasing))) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public long solve(String filePath) {
        return countSafeReports(RowListInput.readInput(filePath));
    }

    public static void main(String[] args) {
        System.out.println("Result " + new Day2Problem2().solve("day2-problem1-input.csv"));
    }
}
