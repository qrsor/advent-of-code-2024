package pl.qrsor.adventofcode2024.day10;

import pl.qrsor.adventofcode2024.CharMatrixInput;

class Day10Problem2 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day10Problem2().solve("day10-problem1-input"));
    }

    long sumOfTrails(char[][] map) {
        var trailMap = new DistinctTrailMap(map);
        return trailMap.trailCount();
    }

    long solve(String filePath) {
        return sumOfTrails(CharMatrixInput.readInput(filePath));
    }
}
