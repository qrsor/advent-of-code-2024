package pl.qrsor.adventofcode2024.day9;

class File {

    private final int id;
    private int size;
    private boolean canMove = true;

    File(int id, int size) {
        this.id = id;
        this.size = size;
    }

    boolean isFreeSpace() {
        return id == -1;
    }

    boolean hasData() {
        return !isFreeSpace();
    }

    void shrinkBy(File rightFile) {
        size = size - rightFile.size();
    }

    int size() {
        return size;
    }

    public int id() {
        return id;
    }

    @Override
    public String toString() {
        return "[id: " + id + " size: " + size + "]";
    }

    public void cannotMove() {
        canMove = false;
    }

    public boolean checkCanMove() {
        return canMove;
    }
}
