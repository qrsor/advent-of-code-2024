package pl.qrsor.adventofcode2024.day6;

import pl.qrsor.adventofcode2024.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FastGuard2 {

    private final int rowCount;
    private final int colCount;
    private final List<Footstep> footsteps = new ArrayList<>();
    private final Set<Position> obstacles = new HashSet<>();
    private final Position extraObstacle;
    private Position position;
    private Direction direction;

    FastGuard2(char[][] map, Position extraObstacle) throws WalkingInCirclesException {
        this.rowCount = map.length;
        this.colCount = map[0].length;

        this.analyzeMap(map);

        this.extraObstacle = extraObstacle;
        this.obstacles.add(extraObstacle);
        markCurrentPositionWalked();
    }

    private void analyzeMap(char[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                char tile = map[row][column];
                if (tile == '#') {
                    this.obstacles.add(new Position(row, column));
                } else if (tile == '^') {
                    this.position = new Position(row, column);
                }
            }
        }
    }


    private void markCurrentPositionWalked() throws WalkingInCirclesException {
        if (footsteps.contains(new Footstep(position, direction))) {
//            System.out.println();
//            System.out.println("Cycle detected at " + position);
//            System.out.println();
            throw new WalkingInCirclesException();
        } else {
            footsteps.add(new Footstep(position, direction));
        }
    }

    void walk() throws CannotWalkException, WalkingInCirclesException {
//        drawDebug();

        var nextPosition = calculateNextPosition(direction);

        if (obstacles.contains(nextPosition)) {
            turn();
        } else {
            position = nextPosition;
        }

        verifyCanWalk();
        markCurrentPositionWalked();
    }

    private void drawDebug() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Position drawnPosition = new Position(i, j);
                if (extraObstacle.equals(drawnPosition)) {
                    System.out.print("0");
                } else if (obstacles.contains(drawnPosition)) {
                    System.out.print("#");
                } else if (position.equals(drawnPosition)) {
                    System.out.print(switch (direction) {
                        case UP -> "^";
                        case RIGHT -> ">";
                        case DOWN -> "v";
                        case LEFT -> "<";
                    });
                } else if (footsteps.stream().map(Footstep::position).anyMatch(position -> position.equals(drawnPosition))) {
                    System.out.print("x");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

    private Position calculateNextPosition(Direction direction) {
        return switch (direction) {
            case UP -> new Position(position.row() - 1, position.col());
            case RIGHT -> new Position(position.row(), position.col() + 1);
            case DOWN -> new Position(position.row() + 1, position.col());
            case LEFT -> new Position(position.row(), position.col() - 1);
        };
    }

    private void verifyCanWalk() throws CannotWalkException {
        if (position.isOffMap(rowCount, colCount)) {
            throw new CannotWalkException();
        }
    }

    void turn() {
        direction = lookRight();
    }

    Direction lookRight() {
        return switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }
}
