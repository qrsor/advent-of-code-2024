package pl.qrsor.adventofcode2024.day8;

import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

record Antenna(char frequency, Position position) {
    Set<Antinode> resonates(Antenna otherAntenna, Dimensions mapDimensions, int antinodesLimitPerDirection) throws CannotResonateWithItself {
        if (frequency != otherAntenna.frequency) {
            return Set.of();
        }
        if (this.equals(otherAntenna)) {
            throw new CannotResonateWithItself();
        }

        var horizontalDistance = position.col() - otherAntenna.position().col();
        var verticalDistance = position.row() - otherAntenna.position().row();

        Set<Antinode> antinodes = locateAntinodes(horizontalDistance, verticalDistance, mapDimensions, antinodesLimitPerDirection).stream()
                .map(Antinode::new)
                .collect(Collectors.toSet());

        if (antinodesLimitPerDirection > 1) { // Lazy solution
            antinodes.add(new Antinode(position));
            antinodes.add(new Antinode(otherAntenna.position()));
        }

        return antinodes;
    }

    private Set<Position> locateAntinodes(int horizontalDistance, int verticalDistance, Dimensions mapDimensions, int antinodesLimitPerDirection) {

        var result = new HashSet<Position>();
        Position firstAntinodePosition = new Position(position.row() + verticalDistance, position().col() + horizontalDistance);
        var multiplier = 1;
        var executions = 0;
        while (executions < antinodesLimitPerDirection && firstAntinodePosition.isOnMap(mapDimensions)) {
            result.add(firstAntinodePosition);
            firstAntinodePosition = new Position(position.row() + multiplier * verticalDistance, position().col() + multiplier * horizontalDistance);
            multiplier++;
            executions++;
        }
        multiplier = 3;
        executions = 0;
        Position secondAntinodePosition = new Position(position.row() - 2 * verticalDistance, position().col() - 2 * horizontalDistance);
        while (executions < antinodesLimitPerDirection && secondAntinodePosition.isOnMap(mapDimensions)) {
            result.add(secondAntinodePosition);
            secondAntinodePosition = new Position(position.row() - multiplier * verticalDistance, position().col() - multiplier * horizontalDistance);
            multiplier++;
            executions++;
        }

        return result;
    }
}
