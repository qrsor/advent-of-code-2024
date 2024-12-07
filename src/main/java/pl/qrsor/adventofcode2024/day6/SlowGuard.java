package pl.qrsor.adventofcode2024.day6;

import java.util.HashSet;
import java.util.Set;

class SlowGuard {

    private final Set<Position> obstacles = new HashSet<>();
    private final int rowCount;
    private final int colCount;
    private int loopingFastGuards = 0;
    private Position position;
    private Direction direction = Direction.UP;

    SlowGuard(char[][] map) {
        this.rowCount = map.length;
        this.colCount = map[0].length;

        this.analyzeMap(map);
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

    void walk() throws CannotWalkException {

        if (checkIfFastGuardLoops()) {
            loopingFastGuards++;
        }

        var nextPosition = switch (direction) {
            case UP -> new Position(position.row() - 1, position.col());
            case RIGHT -> new Position(position.row(), position.col() + 1);
            case DOWN -> new Position(position.row() + 1, position.col());
            case LEFT -> new Position(position.row(), position.col() - 1);
        };

        if (obstacles.contains(nextPosition)) {
            turn();
        } else {
            position = nextPosition;
        }

        if (position.row() < 0
                || position.row() == rowCount
                || position.col() < 0
                || position.col() == colCount
        ) {
            throw new CannotWalkException();
        }
    }

    private boolean checkIfFastGuardLoops() {

        var nextPosition = switch (direction) {
            case UP -> new Position(position.row() - 1, position.col());
            case RIGHT -> new Position(position.row(), position.col() + 1);
            case DOWN -> new Position(position.row() + 1, position.col());
            case LEFT -> new Position(position.row(), position.col() - 1);
        };

        if (!obstacles.contains(nextPosition)) {

            FastGuard fastGuard;
            try {
                fastGuard = new FastGuard(rowCount, colCount, new Footstep(position, direction), obstacles);
            } catch (WalkingInCirclesException e) {
                return false; // This is an edge case I think?
            }

            while (true) {
                try {
                    fastGuard.walk();
                } catch (CannotWalkException e) {
                    return false;
                } catch (WalkingInCirclesException e) {
                    return true;
                }

            }

        } else {
            return false;
        }
    }

    void turn() {
        direction = switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }

    public int tellObstaclesAdded() {
        return loopingFastGuards;
    }
}
