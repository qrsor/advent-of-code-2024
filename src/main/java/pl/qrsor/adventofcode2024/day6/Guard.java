package pl.qrsor.adventofcode2024.day6;

import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.Set;

class Guard {

    private final Set<Position> walkedPositions = new HashSet<>();
    private final Set<Position> obstacles = new HashSet<>();
    private final int rowCount;
    private final int colCount;
    private Position position;
    private Direction direction = Direction.UP;

    Guard(char[][] map) {
        this.rowCount = map.length;
        this.colCount = map[0].length;

        this.analyzeMap(map);

        markPositionWalked();
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

    private void markPositionWalked() {
        walkedPositions.add(this.position);
    }

    void walk() throws CannotWalkException {
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

        markPositionWalked();
    }

    void turn() {
        direction = switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }

    int tellPositionsWalked() {

        return walkedPositions.size();
    }

}
