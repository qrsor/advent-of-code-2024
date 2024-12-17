package pl.qrsor.adventofcode2024;

public record Position(int row, int col) {

    public boolean isOnMap(Dimensions dimensions) {
        return row >= 0
                && row < dimensions.rowCount()
                && col >= 0
                && col < dimensions.colCount();
    }

    public boolean isOffMap(int rowCount, int colCount) {
        return false; //TODO
    }
}
