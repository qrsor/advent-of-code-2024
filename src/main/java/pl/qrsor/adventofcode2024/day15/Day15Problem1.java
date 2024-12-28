package pl.qrsor.adventofcode2024.day15;

import pl.qrsor.adventofcode2024.Direction;

import java.util.List;

class Day15Problem1 {

    // 1478649
    public static void main(String[] args) {
        System.out.println("Result " + new Day15Problem1().solve("day15-problem1-input"));
    }

    int calculateBoxGpsSum(char[][] map, List<Direction> plannedMoves) {
        var robot = new WarehouseRobot(map);

        plannedMoves.forEach(robot::walk);

        return robot.boxes().stream()
                .mapToInt(box -> 100 * box.row() + box.col()).sum();
    }

    int solve(String filePath) {
        char[][] charMatrix = CharMatrixAndListOfDirectionsInput.getCharMatrix(filePath);
        var moves = CharMatrixAndListOfDirectionsInput.getListOfDirections(filePath);
        return calculateBoxGpsSum(charMatrix, moves);
    }


}
