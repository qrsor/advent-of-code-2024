package pl.qrsor.adventofcode2024.day14;

import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

public class TeleportingRobot {

    private final Velocity velocity;
    private final Dimensions bathroomSpace;
    private Position startPos;

    public TeleportingRobot(Position startPos, Velocity velocity, Dimensions bathroomSpace) {
        this.startPos = startPos;
        this.velocity = velocity;
        this.bathroomSpace = bathroomSpace;
    }

    public Position travelFor(int travelTime) {

        var row = (startPos.row() + travelTime * velocity.row()) % bathroomSpace.rowCount();
        var col = (startPos.col() + travelTime * velocity.col()) % bathroomSpace.colCount();

        if (row < 0) {
            row = bathroomSpace.rowCount() + row;
        }

        if (col < 0) {
            col = bathroomSpace.colCount() + col;
        }

        return startPos = new Position(row, col);
    }

    public Position position() {
        return startPos;
    }
}
