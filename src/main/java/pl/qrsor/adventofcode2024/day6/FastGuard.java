package pl.qrsor.adventofcode2024.day6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FastGuard {

    private final int rowCount;
    private final int colCount;
    private final List<Footstep> footsteps = new ArrayList<>();
    private final Set<Position> obstacles = new HashSet<>();
    private Position position;
    private Direction direction;

    FastGuard(int rowCount, int colCount, Footstep startFootstep, Set<Position> obstacles) throws WalkingInCirclesException {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.position = startFootstep.position();
        this.direction = startFootstep.comingFrom();

        this.obstacles.addAll(obstacles);
        this.obstacles.add(calculateNextPosition(this.direction));
        markCurrentPositionWalked();
    }


    private void markCurrentPositionWalked() throws WalkingInCirclesException {
        if (footsteps.contains(new Footstep(position, direction))) {

            drawFootsteps();

            throw new WalkingInCirclesException();
        } else {
            footsteps.add(new Footstep(position, direction));
        }
    }

    private void drawFootsteps() {
        
    }

    void walk() throws CannotWalkException, WalkingInCirclesException {
        var nextPosition = calculateNextPosition(direction);

        if (obstacles.contains(nextPosition)) {
            turn();
        } else {
            position = nextPosition;
        }

        verifyCanWalk();
        markCurrentPositionWalked();
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
