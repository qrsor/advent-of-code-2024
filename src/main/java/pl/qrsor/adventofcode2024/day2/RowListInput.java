package pl.qrsor.adventofcode2024.day2;

import pl.qrsor.adventofcode2024.Input;

import java.util.Arrays;
import java.util.List;

public class RowListInput {
    static List<List<Integer>> readInput(String filePath) {
        var rawData = Input.readFile(filePath);
        return parseRawData(rawData);
    }

    private static List<List<Integer>> parseRawData(List<String> rawData) {
        return rawData.stream().map(line -> Arrays.stream(line.split(" ")).map(Integer::valueOf).toList()).toList();
    }
}
