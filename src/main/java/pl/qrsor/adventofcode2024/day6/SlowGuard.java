package pl.qrsor.adventofcode2024.day6;

import pl.qrsor.adventofcode2024.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class SlowGuard {

    private final Set<Position> obstacles = new HashSet<>();
    private final int rowCount;
    private final int colCount;
    private final Set<Position> addedObstacles = new HashSet<>();
    private final Set<Footstep> addedObstacleFootsteps = new HashSet<>();
    private final List<Footstep> footsteps = new ArrayList<>();
    private int loopingFastGuards = 0;
    private Position position;
    private Direction direction = Direction.UP;
    private Position startPosition;

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
                    this.startPosition = new Position(row, column);
                }
            }
        }
    }

    void walk() throws CannotWalkException {

//        drawDebug();

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

        markCurrentPositionWalked();
    }

    private void markCurrentPositionWalked() //throws WalkingInCirclesException {
    {
//        if (footsteps.contains(new Footstep(position, direction))) {
//        System.out.println();
//        System.out.println("Cycle detected at " + position);
//        System.out.println();
//            throw new WalkingInCirclesException();
//        } else {
        footsteps.add(new Footstep(position, direction));
//        }
    }

    private void drawDebug() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Position drawnPosition = new Position(i, j);
                if (obstacles.contains(drawnPosition)) {
                    System.out.print("#");
                } else if (position.equals(drawnPosition)) {
                    System.out.print(switch (direction) {
                        case UP -> "^";
                        case RIGHT -> ">";
                        case DOWN -> "v";
                        case LEFT -> "<";
                    });
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

    private boolean checkIfFastGuardLoops() {

        var nextPosition = switch (direction) {
            case UP -> new Position(position.row() - 1, position.col());
            case RIGHT -> new Position(position.row(), position.col() + 1);
            case DOWN -> new Position(position.row() + 1, position.col());
            case LEFT -> new Position(position.row(), position.col() - 1);
        };

        if (obstacles.contains(nextPosition) || nextPosition.isOffMap(rowCount, colCount)) {
            return false;
        } else {
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
                    addedObstacles.add(nextPosition);
                    addedObstacleFootsteps.add(new Footstep(nextPosition, direction));
                    return true;
                }
            }
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
        System.out.println("Added obstacles " + addedObstacles.size());
        System.out.println("Added footprints " + addedObstacleFootsteps.size());
        System.out.println("Footsteps " + footsteps.size());
        System.out.println("Positions " + footsteps.stream().map(Footstep::position).collect(Collectors.toSet()).size());
        return loopingFastGuards;
    }
}
