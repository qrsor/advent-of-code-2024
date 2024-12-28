package pl.qrsor.adventofcode2024.day15;

import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

record Box(Position left, Position right) {
    public Box shift(Direction direction) {
        return new Box(calculateNextPosition(left(), direction), calculateNextPosition(right(), direction));
    }

    private Position calculateNextPosition(Position position, Direction direction) {
        return switch (direction) {
            case UP -> new Position(position.row() - 1, position.col());
            case RIGHT -> new Position(position.row(), position.col() + 1);
            case DOWN -> new Position(position.row() + 1, position.col());
            case LEFT -> new Position(position.row(), position.col() - 1);
        };
    }
}
