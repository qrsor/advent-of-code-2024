package pl.qrsor.adventofcode2024.day8;

import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

record Antinode(Position position) {
    boolean isOnMap(Dimensions dimensions) {
        return position.isOnMap(dimensions);
    }
}
