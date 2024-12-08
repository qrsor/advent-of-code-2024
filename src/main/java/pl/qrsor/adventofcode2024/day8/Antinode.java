package pl.qrsor.adventofcode2024.day8;

record Antinode(Position position) {
    boolean isOnMap(Dimensions dimensions) {
        return position.isOnMap(dimensions);
    }
}
