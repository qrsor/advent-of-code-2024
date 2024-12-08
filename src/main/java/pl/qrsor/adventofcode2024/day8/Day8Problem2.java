package pl.qrsor.adventofcode2024.day8;

import pl.qrsor.adventofcode2024.CharMatrixInput;

class Day8Problem2 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day8Problem2().solve("day8-problem1-input"));
    }

    int countAntinodes(char[][] map) {
        var locator = new AntinodeLocator();

        return locator.countAntinodes(map, Integer.MAX_VALUE);
    }

    int solve(String filePath) {
        return countAntinodes(CharMatrixInput.readInput(filePath));
    }


}
