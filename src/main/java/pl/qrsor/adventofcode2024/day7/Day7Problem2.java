package pl.qrsor.adventofcode2024.day7;

import java.util.List;

class Day7Problem2 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day7Problem2().solve("day7-problem1-input"));
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

        var operators = ThreeCharacterPermutations.generatePermutationsUpTo(items.size() - 1);

        for (List<ThreeCharacterPermutations.Symbol> operatorList : operators) {
            Long postOperation = items.getFirst();
            for (int i = 1; i < items.size(); i++) {
                postOperation = switch (operatorList.get(i - 1)) {
                    case ThreeCharacterPermutations.Symbol.PLUS -> postOperation + items.get(i);
                    case ThreeCharacterPermutations.Symbol.ASTERISK -> postOperation * items.get(i);
                    case ThreeCharacterPermutations.Symbol.CONCATENATION -> {
                        var stringOperation = postOperation.toString();
                        var stringItem = items.get(i).toString();
                        var concatenated = stringOperation + stringItem;
                        yield Long.valueOf(concatenated);
                    }
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
