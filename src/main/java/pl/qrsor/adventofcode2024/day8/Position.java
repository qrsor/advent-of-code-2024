package pl.qrsor.adventofcode2024.day8;

record Position(int row, int col) {

    boolean isOnMap(Dimensions dimensions) {
        return row >= 0
                && row < dimensions.rowCount()
                && col >= 0
                && col < dimensions.colCount();
    }
}
