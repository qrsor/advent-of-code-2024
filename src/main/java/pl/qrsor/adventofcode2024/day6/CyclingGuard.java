package pl.qrsor.adventofcode2024.day6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class CyclingGuard {

    private final Set<Position> addedObstacles = new HashSet<>();
    private final int rowCount;
    private final int colCount;
    private final List<Footstep> footsteps = new ArrayList<>();
    private final Set<Position> obstacles = new HashSet<>();
    private String name = "Boss";
    private int step;
    private Position position;
    private Direction direction = Direction.UP;

    CyclingGuard(char[][] map) throws WalkingInCirclesException {
        this.rowCount = map.length;
        this.colCount = map[0].length;

        this.analyzeMap(map);

        markPositionWalked();
    }

    CyclingGuard(List<Footstep> footsteps, Set<Position> obstacles, int rowCount, int colCount, Direction direction) {
        this.footsteps.addAll(footsteps);
        this.obstacles.addAll(obstacles);
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.direction = direction;
        this.position = footsteps.getLast().position();
        this.name = "Peon";
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

    private void markPositionWalked() throws WalkingInCirclesException {
//        System.out.println(name + " " + step++ + ": " + position + " " + direction.toString() + " walked");
        if (footsteps.contains(new Footstep(position, direction))) {
            throw new WalkingInCirclesException();
        } else {
            footsteps.add(new Footstep(position, direction));
        }
    }

    void walk() throws CannotWalkException, WalkingInCirclesException {
        var nextPosition = calculateNextPosition(direction);

        if (obstacles.contains(nextPosition)) {
            turn();
        } else {
            position = nextPosition;
        }

        verifyCanWalk();
        markPositionWalked();

//        if (lookForFootstepsIfTurnWasMade()) {
//            addObstacleInFront();
//        }

        if (name.equals("Boss") && wouldGuardWalkInCirclesIfBlocked()) {
            addObstacleInFront();
        }
    }

    private boolean wouldGuardWalkInCirclesIfBlocked() {
        var obstaclesWithExtraObstacle = new HashSet<>(obstacles);

        var newObstaclePosition = calculateNextPosition(direction);
        obstaclesWithExtraObstacle.add(newObstaclePosition);

        CyclingGuard ghostGuard = new CyclingGuard(footsteps, obstaclesWithExtraObstacle, rowCount, colCount, direction);

        var ghostSteps = 0;
        while (ghostSteps < 10000) {
            try {
                ghostGuard.walk();
                ghostSteps++;
            } catch (CannotWalkException e) {
                return false;
            } catch (WalkingInCirclesException e) {
                return true;
            }
        }

        return false;
    }

    private Position calculateNextPosition(Direction direction) {
        return switch (direction) {
            case UP -> new Position(position.row() - 1, position.col());
            case RIGHT -> new Position(position.row(), position.col() + 1);
            case DOWN -> new Position(position.row() + 1, position.col());
            case LEFT -> new Position(position.row(), position.col() - 1);
        };
    }

//    private Position calculateNextPositionFrom(Position position, Direction direction) {
//        return switch (direction) {
//            case UP -> new Position(position.row() - 1, position.col());
//            case RIGHT -> new Position(position.row(), position.col() + 1);
//            case DOWN -> new Position(position.row() + 1, position.col());
//            case LEFT -> new Position(position.row(), position.col() - 1);
//        };
//    }

//    private boolean lookForFootstepsIfTurnWasMade() {
//        var lookingDirection = lookRight();
//
//        Position nextPosition = position;
//        do {
//            nextPosition = calculateNextPositionFrom(nextPosition, lookingDirection);
//            var obstacleAhead = obstacles.contains(nextPosition);
//            if (!obstacleAhead && footsteps.contains(new Footstep(nextPosition, lookingDirection))) {
//                return true;
//            }
//        } while (nextPosition.isOnMap(rowCount, colCount));
//
//        return false;
//    }

    private void verifyCanWalk() throws CannotWalkException {
        if (position.isOffMap(rowCount, colCount)) {
            throw new CannotWalkException();
        }
    }

    private void addObstacleInFront() {
        var newObstaclePosition = calculateNextPosition(direction);
        if (!obstacles.contains(newObstaclePosition)) {
            addedObstacles.add(newObstaclePosition);
//            System.out.println(newObstaclePosition + " obstacle added");
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

    int tellObstaclesAdded() {
//        System.out.println("addedObstacles");
//        for (Position addedObstacle : addedObstacles) {
//            System.out.println(addedObstacle);
//        }
        return addedObstacles.size();
    }

}
