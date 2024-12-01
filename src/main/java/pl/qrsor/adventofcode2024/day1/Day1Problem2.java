package pl.qrsor.adventofcode2024.day1;

import java.util.HashMap;

public class Day1Problem2 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day1Problem2().solve("day1-problem1-input.csv"));
    }

    public int solve(String filePath) {
        return similarityScore(ListsInput.readInput(filePath));
    }

    private int similarityScore(ListPair listPair) {

        var frequencyMap = new HashMap<Integer, Integer>();
        listPair.rightList().forEach(element -> {
            frequencyMap.computeIfPresent(element, (key, oldValue) -> ++oldValue);
            frequencyMap.putIfAbsent(element, 1);
        });

        return listPair.leftList().stream().reduce(0, (result, element) -> result + element * frequencyMap.getOrDefault(element, 0));
    }
}
