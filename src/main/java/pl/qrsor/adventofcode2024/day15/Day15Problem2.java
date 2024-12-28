package pl.qrsor.adventofcode2024.day15;

import pl.qrsor.adventofcode2024.CharMatrixInput;
import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Input;

import java.util.List;

class Day15Problem2 {

    // 1494695 too low
    // 1500487 too high
    // 1487224 ???
    // 1495455
    public static void main(String[] args) {
        System.out.println("Result " + new Day15Problem2().solve("day15-problem1-input"));
    }

    int calculateBoxGpsSum(char[][] map, List<Direction> plannedMoves) {
        var robot = new WideWarehouseRobot(map);

        plannedMoves.forEach(robot::walk);

        return robot.boxes().stream()
                .mapToInt(box -> 100 * box.row() + box.col()).sum();
    }

    int solve(String filePath) {
        char[][] charMatrix = CharMatrixInput.readInput(filePath + "1");
        List<String> directions = Input.readFile(filePath + "2");

        var moves = String.join("", directions).chars()
                .mapToObj(i -> (char) i)
                .map(c -> switch (c) {
                    case '^' -> Direction.UP;
                    case '>' -> Direction.RIGHT;
                    case 'v' -> Direction.DOWN;
                    case '<' -> Direction.LEFT;
                    default -> throw new IllegalStateException("Unexpected value: " + c);
                }).toList();
        return calculateBoxGpsSum(charMatrix, moves);
    }
}
