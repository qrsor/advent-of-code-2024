package pl.qrsor.adventofcode2024.day7;

import java.util.List;

class Day7Problem1 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day7Problem1().solve("day7-problem1-input"));
    }

    Long countDistinctVisitedPositions(List<ResultAndItems> results) {

        long resultLong = 0L;
        for (ResultAndItems result : results) {
            resultLong += processOneRow(result);
        }

        return resultLong;
    }

    private long processOneRow(ResultAndItems result) {
        var expected = result.result();
        var items = result.items();

        var operators = CharacterPermutations.generatePermutationsUpTo(items.size() - 1);

        for (List<CharacterPermutations.Symbol> operatorList : operators) {
            Long postOperation = items.getFirst();
            for (int i = 1; i < items.size(); i++) {
                postOperation = switch (operatorList.get(i - 1)) {
                    case CharacterPermutations.Symbol.PLUS -> postOperation + items.get(i);
                    case CharacterPermutations.Symbol.ASTERISK -> postOperation * items.get(i);
                };
            }

            if (postOperation.equals(expected)) {
                return expected;
            }
        }

        return 0;
    }

    Long solve(String filePath) {
        return countDistinctVisitedPositions(SumAndItemsInput.readInput(filePath));
    }


}
