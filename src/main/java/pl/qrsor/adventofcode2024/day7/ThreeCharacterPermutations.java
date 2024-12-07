package pl.qrsor.adventofcode2024.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreeCharacterPermutations {
    // Memoization map to store permutations for each length
    private static final Map<Integer, List<List<Symbol>>> PERMUTATIONS_CACHE = new HashMap<>();

    public static List<List<Symbol>> generatePermutationsUpTo(int maxLength) {
        // Generate permutations for each length from 0 to maxLength

        if (PERMUTATIONS_CACHE.containsKey(maxLength)) {
            return PERMUTATIONS_CACHE.get(maxLength);
        }

        for (int n = 0; n <= maxLength; n++) {
            List<List<Symbol>> permutationsForLength = generateSymbolPermutations(n);
            PERMUTATIONS_CACHE.put(n, permutationsForLength);
        }

        return PERMUTATIONS_CACHE.get(maxLength);
    }

    public static List<List<Symbol>> generateSymbolPermutations(int n) {
        List<List<Symbol>> result = new ArrayList<>();
        generatePermutationsHelper(new ArrayList<>(), n, result);
        return result;
    }

    private static void generatePermutationsHelper(
            List<Symbol> current,
            int remainingLength,
            List<List<Symbol>> result
    ) {
        // Base case: if the list reaches the desired length, add to results
        if (remainingLength == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Recursive case: add PLUS and ASTERISK
        current.add(Symbol.PLUS);
        generatePermutationsHelper(current, remainingLength - 1, result);
        current.remove(current.size() - 1);

        current.add(Symbol.ASTERISK);
        generatePermutationsHelper(current, remainingLength - 1, result);
        current.remove(current.size() - 1);

        current.add(Symbol.CONCATENATION);
        generatePermutationsHelper(current, remainingLength - 1, result);
        current.remove(current.size() - 1);
    }

    // Enum to represent the two characters
    public enum Symbol {
        PLUS("+"),
        ASTERISK("*"),
        CONCATENATION("||");

        private final String value;

        Symbol(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}