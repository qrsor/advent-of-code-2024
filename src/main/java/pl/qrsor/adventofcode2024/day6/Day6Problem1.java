package pl.qrsor.adventofcode2024.day6;

import pl.qrsor.adventofcode2024.CharMatrixInput;

class Day6Problem1 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day6Problem1().solve("day6-problem1-input"));
    }

    int countDistinctVisitedPositions(char[][] map) {
        var guard = new Guard(map);

        var journeyContinues = true;
        while (journeyContinues) {
            try {
                guard.walk();
            } catch (CannotWalkException e) {
                journeyContinues = false;
            }
        }

        return guard.tellPositionsWalked();
    }

    int solve(String filePath) {
        return countDistinctVisitedPositions(CharMatrixInput.readInput(filePath));
    }


}
