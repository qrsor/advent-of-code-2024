package pl.qrsor.adventofcode2024.day4;

import pl.qrsor.adventofcode2024.Input;

import java.util.List;

public class CharMatrixInput {
    static char[][] readInput(String filePath) {
        List<String> strings = Input.readFile(filePath);

        return parseStrings(strings);
    }

    public static char[][] parseStrings(List<String> strings) {
        return strings.stream().map(String::toCharArray).toList()
                .toArray(new char[0][]);
    }
}
