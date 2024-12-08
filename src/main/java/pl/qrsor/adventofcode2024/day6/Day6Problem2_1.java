package pl.qrsor.adventofcode2024.day6;

import pl.qrsor.adventofcode2024.CharMatrixInput;

class Day6Problem2_1 {

    public static void main(String[] args) throws WalkingInCirclesException {
        System.out.println("Result " + new Day6Problem2_1().solve("day6-problem1-input"));
    }

    int countObstaclesAdded(char[][] map) {
        //TODO take the 4939 positions
        //map to fast guards with the extra position
        // count loops
        return 0;
    }

    int solve(String filePath) throws WalkingInCirclesException {
        return countObstaclesAdded(CharMatrixInput.readInput(filePath));
    }


}
