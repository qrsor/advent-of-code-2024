package pl.qrsor.adventofcode2024.day14;

import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

public class Quadrant {
    private final int rowMean;
    private final int colMean;
    private int q1 = 0;
    private int q2 = 0;
    private int q3 = 0;
    private int q4 = 0;

    public Quadrant(Dimensions bathroomDimensions) {
        rowMean = bathroomDimensions.rowCount() / 2;
        colMean = bathroomDimensions.colCount() / 2;
    }

    void quantify(Position position) {
        if (position.row() < rowMean) {
            if (position.col() < colMean) {
                q1++;
            } else if (position.col() > colMean) {
                q2++;
            }
        } else if (position.row() > rowMean) {
            if (position.col() < colMean) {
                q3++;
            } else if (position.col() > colMean) {
                q4++;
            }
        }
    }

    int safeFactor() {
        return q1 * q2 * q3 * q4;
    }
}
