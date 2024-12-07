package pl.qrsor.adventofcode2024.day7;

import pl.qrsor.adventofcode2024.Input;

import java.util.Arrays;
import java.util.List;

public class SumAndItemsInput {
    public static List<ResultAndItems> readInput(String filePath) {
        List<String> strings = Input.readFile(filePath);

        return parseStrings(strings);
    }

    public static List<ResultAndItems> parseStrings(List<String> strings) {
        return strings.stream().map(line -> {
            String[] tokens = line.split(" ");
            long result = Long.parseLong(tokens[0].replace(":", ""));
            List<Long> items = Arrays.stream(tokens).skip(1).map(Long::valueOf).toList();
            return new ResultAndItems(result, items);
        }).toList();
    }
}
