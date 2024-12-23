package pl.qrsor.adventofcode2024;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Position(int row, int col) {

    public boolean isOnMap(Dimensions dimensions) {
        return row >= 0
                && row < dimensions.rowCount()
                && col >= 0
                && col < dimensions.colCount();
    }

    public boolean isOffMap(Dimensions dimensions) {
        return !isOnMap(dimensions);
    }

    public Position neighbour(Direction direction) {
        return switch (direction) {
            case UP -> new Position(row - 1, col);
            case DOWN -> new Position(row + 1, col);
            case LEFT -> new Position(row, col - 1);
            case RIGHT -> new Position(row, col + 1);
        };
    }

    public Set<Position> neighbours(Dimensions mapDimensions) {
        return Stream.of(Direction.values())
                .map(this::neighbour)
                .filter(position -> position.isOnMap(mapDimensions))
                .collect(Collectors.toSet());
    }
}
