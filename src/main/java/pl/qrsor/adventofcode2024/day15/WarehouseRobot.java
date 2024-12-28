package pl.qrsor.adventofcode2024.day15;


import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.Set;

class WarehouseRobot {

    private final Set<Position> walls = new HashSet<>();
    private final Set<Position> boxes = new HashSet<>();
    private Position position;
    private Dimensions mapDimensions;

    WarehouseRobot(char[][] warehouse) {
        this.analyzeMap(warehouse);
    }

    private void analyzeMap(char[][] warehouse) {
        var rows = warehouse.length;
        var cols = warehouse[0].length;
        this.mapDimensions = new Dimensions(rows, cols);

        for (int row = 0; row < rows; row++) {
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
//        drawDebug(null);
    }

    private void drawDebug(Direction walkingDirection) {
        for (int i = 0; i < mapDimensions.rowCount(); i++) {
            for (int j = 0; j < mapDimensions.colCount(); j++) {
                Position drawnPosition = new Position(i, j);
                if (walls.contains(drawnPosition)) {
                    System.out.print("#");
                } else if (boxes.contains(drawnPosition)) {
                    System.out.print("o");
                } else if (position.equals(drawnPosition)) {
                    System.out.print("@");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("Going " + walkingDirection);
        System.out.println();
    }

    Position walk(Direction direction) {

//        drawDebug(direction);

        var nextPosition = calculateNextPosition(position, direction);
        if (walls.contains(nextPosition)) {
            return position;
        }
        if (boxes.contains(nextPosition)) {
            var movedBoxes = moveBox(nextPosition, direction);
            if (movedBoxes) {
                position = nextPosition;
            }
        } else {
            position = nextPosition;
        }


        return position;
    }

    private boolean moveBox(Position box, Direction direction) {
        var nextPosition = calculateNextPosition(box, direction);
        if (walls.contains(nextPosition)) {
            return false;
        }

        if (boxes.contains(nextPosition)) {
            var movedNextBox = moveBox(nextPosition, direction);
            if (movedNextBox) {
                boxes.remove(box);
                boxes.add(nextPosition);
                return true;
            } else {
                return false;
            }
        }

        boxes.remove(box);
        boxes.add(nextPosition);
        return true;
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

//        drawDebug(null);

        return boxes;
    }

}
