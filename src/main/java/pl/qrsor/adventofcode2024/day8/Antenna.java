package pl.qrsor.adventofcode2024.day8;

import java.util.Set;
import java.util.stream.Collectors;

record Antenna(char frequency, Position position) {
    Set<Antinode> resonates(Antenna otherAntenna, Dimensions mapDimensions) throws CannotResonateWithItself {
        if (frequency != otherAntenna.frequency) {
            return Set.of();
        }
        if (this.equals(otherAntenna)) {
            throw new CannotResonateWithItself();
        }

        var horizontalDistance = position.col() - otherAntenna.position().col();
        var verticalDistance = position.row() - otherAntenna.position().row();

        var antinodePositions = locateAntinodes(horizontalDistance, verticalDistance);

        return antinodePositions.stream()
                .filter(position -> position.isOnMap(mapDimensions))
                .map(Antinode::new)
                .collect(Collectors.toSet());
    }

    private Set<Position> locateAntinodes(int horizontalDistance, int verticalDistance) {
        Position firstAntinodePosition = new Position(position.row() + verticalDistance, position().col() + horizontalDistance);
        Position secondAntinodePosition = new Position(position.row() - 2 * verticalDistance, position().col() - 2 * horizontalDistance);

        return Set.of(firstAntinodePosition, secondAntinodePosition);
    }
}
