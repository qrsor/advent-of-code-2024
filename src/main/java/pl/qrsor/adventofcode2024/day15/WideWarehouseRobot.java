package pl.qrsor.adventofcode2024.day15;


import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WideWarehouseRobot {

    private final Set<Position> walls = new HashSet<>();
    private final Set<Box> boxes = new HashSet<>();
    private Position position;
    private Dimensions mapDimensions;

    WideWarehouseRobot(char[][] warehouse) {
        this.analyzeMap(warehouse);
    }

    private void analyzeMap(char[][] warehouse) {
        var rows = warehouse.length;
        var cols = warehouse[0].length;
        this.mapDimensions = new Dimensions(rows, cols * 2);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < warehouse[row].length; column++) {
                char tile = warehouse[row][column];
                if (tile == '#') {
                    this.walls.add(new Position(row, 2 * column));
                    this.walls.add(new Position(row, 2 * column + 1));
                } else if (tile == 'O') {
                    this.boxes.add(new Box(new Position(row, 2 * column), new Position(row, 2 * column + 1)));
                } else if (tile == '@') {
                    this.position = new Position(row, 2 * column);
                }
            }
        }
        drawDebug(null);
    }

    private void drawDebug(Direction walkingDirection) {

//        System.out.println("Boxes: " + boxes.size());
//
//        for (int i = 0; i < mapDimensions.rowCount(); i++) {
//            for (int j = 0; j < mapDimensions.colCount(); j++) {
//                Position drawnPosition = new Position(i, j);
//                if (walls.contains(drawnPosition)) {
//                    System.out.print("#");
//                } else if (boxes.stream().map(Box::left).collect(Collectors.toSet()).contains(drawnPosition)) {
//                    System.out.print("[]");
//                    j++;
//                } else if (position.equals(drawnPosition)) {
//                    System.out.print("@");
//                } else {
//                    System.out.print(".");
//                }
//            }
//            System.out.println();
//        }
//        System.out.println("Going " + walkingDirection);
//        System.out.println();
    }

    Position walk(Direction direction) {

        drawDebug(direction);

        var nextPosition = calculateNextPosition(position, direction);
        if (walls.contains(nextPosition)) {
            return position;
        }
        if (boxPositions().contains(nextPosition)) {
            var box = getBoxByPosition(nextPosition);
            var movedBoxes = moveBox(box, direction);
            if (movedBoxes) {
                position = nextPosition;
            }
        } else {
            position = nextPosition;
        }


        return position;
    }

    private Box getBoxByPosition(Position nextPosition) {
        return boxes.stream().filter(b -> nextPosition.equals(b.left()) || nextPosition.equals(b.right())).findFirst().get();
    }

    private boolean moveBox(Box box, Direction direction) {

        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            return moveBoxToHorizontally(box, direction);
        } else {
            return moveBoxVertically(box, direction);
        }
    }

    private boolean moveBoxVertically(Box box, Direction direction) {

        var nextPositions = Set.of(calculateNextPosition(box.left(), direction), calculateNextPosition(box.right(), direction));

        if (nextPositions.stream().anyMatch(walls::contains)) {
            return false;
        }

        Set<Box> boxesToMove = nextPositions.stream().filter(boxPositions()::contains)
                .map(this::getBoxByPosition)
                .collect(Collectors.toSet());

        if (!boxesToMove.isEmpty()) {
            var canMove = checkCanMoveBoxVertically(box, direction);
            var allMoved = canMove && boxesToMove.stream().allMatch(b -> moveBoxVertically(b, direction));
            if (allMoved) {
                var shiftedBox = box.shift(direction);
                boxes.remove(box);
                boxes.add(shiftedBox);
                return true;
            } else {
                return false;
            }
        }

        boxes.remove(box);
        boxes.add(box.shift(direction));

        drawDebug(direction);

        return true;
    }

    private boolean checkCanMoveBoxVertically(Box box, Direction direction) {

        var nextPositions = Set.of(calculateNextPosition(box.left(), direction), calculateNextPosition(box.right(), direction));

        if (nextPositions.stream().anyMatch(walls::contains)) {
            return false;
        }

        Set<Box> boxesToMove = nextPositions.stream().filter(boxPositions()::contains)
                .map(this::getBoxByPosition)
                .collect(Collectors.toSet());

        if (!boxesToMove.isEmpty()) {
            return boxesToMove.stream().allMatch(b -> checkCanMoveBoxVertically(b, direction));
        }

        return true;
    }

    private boolean moveBoxToHorizontally(Box box, Direction direction) {

        var nextPosition = calculateNextPosition(direction == Direction.LEFT ? box.left() : box.right(), direction);
        if (walls.contains(nextPosition)) {
            return false;
        }

        if (boxPositions().contains(nextPosition)) {
            var movedNextBox = moveBox(getBoxByPosition(nextPosition), direction);
            if (movedNextBox) {

                var shiftedBox = box.shift(direction);
                boxes.remove(box);
                boxes.add(shiftedBox);
                return true;
            } else {
                return false;
            }
        }

        boxes.remove(box);
        boxes.add(box.shift(direction));
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

    private Set<Position> boxPositions() {
        return boxes.stream().flatMap(b -> Stream.of(b.left(), b.right())).collect(Collectors.toSet());
    }

    List<Position> boxes() {

        drawDebug(null);

        return boxes.stream().map(Box::left).collect(Collectors.toList());
    }

}
