package pl.qrsor.adventofcode2024.day10;

import pl.qrsor.adventofcode2024.Position;

import java.util.Set;

public class TrailTile {
    private final Set<TrailTile> nextPosition;
    private final Position position;

    public TrailTile(Position position, Set<TrailTile> nextPosition) {
        this.position = position;
        this.nextPosition = nextPosition;
    }

    public Position position() {
        return position;
    }

    public Set<TrailTile> next() {
        return nextPosition;
    }
}
