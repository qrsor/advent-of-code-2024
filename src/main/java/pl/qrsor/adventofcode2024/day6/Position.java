package pl.qrsor.adventofcode2024.day6;

record Position(int row, int col) {
    boolean isOffMap(int rowCount, int colCount) {
        return row < 0
                || row >= rowCount
                || col < 0
                || col >= colCount;
    }

    public boolean isOnMap(int rowCount, int colCount) {
        return !isOffMap(rowCount, colCount);
    }
}
