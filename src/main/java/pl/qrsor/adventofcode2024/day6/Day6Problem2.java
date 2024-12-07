package pl.qrsor.adventofcode2024.day6;

import pl.qrsor.adventofcode2024.CharMatrixInput;

class Day6Problem2 {

    public static void main(String[] args) throws WalkingInCirclesException {
        System.out.println("Result " + new Day6Problem2().solve("day6-problem1-input.csv"));
    }

    int countDistinctVisitedPositions(char[][] map) throws WalkingInCirclesException {
        var guard = new SlowGuard(map);

        var journeyContinues = true;
        while (journeyContinues) {
            try {
                guard.walk();
            } catch (CannotWalkException e) {
                journeyContinues = false;
            }
        }

        return guard.tellObstaclesAdded();
    }

    int solve(String filePath) throws WalkingInCirclesException {
        return countDistinctVisitedPositions(CharMatrixInput.readInput(filePath));
    }


}
