package pl.qrsor.adventofcode2024.day15;


import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.Set;

class WarehouseRobot {

    private final Set<Position> walls = new HashSet<>();
    private final Set<Position> boxes = new HashSet<>();
    private Position position;

    WarehouseRobot(char[][] warehouse) {
        this.analyzeMap(warehouse);
    }

    private void analyzeMap(char[][] warehouse) {
        for (int row = 0; row < warehouse.length; row++) {
            for (int column = 0; column < warehouse[row].length; column++) {
                char tile = warehouse[row][column];
                if (tile == '#') {
                    this.walls.add(new Position(row, column));
                } else if (tile == 'O') {
                    this.boxes.add(new Position(row, column));
                } else if (tile == '@') {
                    this.position = new Position(row, column);
                }
            }
        }
    }

    Position walk(Direction direction) {

        var nextPosition = calculateNextPosition(position, direction);
        if (walls.contains(nextPosition)) {
            return position;
        }
        if (boxes.contains(nextPosition)) {
            var nextNextPosition = calculateNextPosition(nextPosition, direction);
            if (walls.contains(nextNextPosition) || boxes.contains(nextNextPosition)) {
                return position;
            } else {
                // remove nextPosition from boxes
                boxes.remove(nextPosition);
                // add nextNextPost pos to boxes
                boxes.add(nextNextPosition);
            }
        }

        position = nextPosition;
        return position;
    }

    private Position calculateNextPosition(Position position, Direction direction) {
        return switch (direction) {
            case UP -> new Position(position.row() - 1, position.col());
            case RIGHT -> new Position(position.row(), position.col() + 1);
            case DOWN -> new Position(position.row() + 1, position.col());
            case LEFT -> new Position(position.row(), position.col() - 1);
        };
    }

    Set<Position> boxes() {
        return boxes;
    }

}
