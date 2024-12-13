package pl.qrsor.adventofcode2024.day12;

import pl.qrsor.adventofcode2024.CharMatrixInput;
import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Day12Problem1_2 {

    private final Set<Position> visitedPlots = new HashSet<Position>();
    private Dimensions farmDimensions;
    private char[][] farm;

    public static void main(String[] args) {
        System.out.println("Result " + new Day12Problem1_2().solve("day12-problem1-input-1"));
    }

    public int solve(String filePath) {
        return calculateFencePrice(CharMatrixInput.readInput(filePath));
    }

    private int calculateFencePrice(char[][] farm) {

        this.farm = farm;
        farmDimensions = new Dimensions(farm.length, farm[0].length);


        var result = 0;

        for (int row = 0; row < farmDimensions.rowCount(); row++) {
            for (int col = 0; col < farmDimensions.colCount(); col++) {
                var plotPosition = new Position(row, col);
                if (!visitedPlots.contains(plotPosition)) {
                    visitedPlots.add(plotPosition);
                    AtomicInteger area = new AtomicInteger(1);
                    var perimeter = visit(plotPosition, area);

                    result += area.get() * perimeter;
                }
            }
        }

        return result;
    }

    private int visit(Position plotPosition, AtomicInteger area) {
        // if position above is off map or different type add perimeter +1;
        //checkLeft

        var perimeter = 0;
        char type = getType(plotPosition);

        perimeter += visitAdjacentPlot(area, plotPosition.row(), plotPosition.col() - 1, type);
        perimeter += visitAdjacentPlot(area, plotPosition.row(), plotPosition.col() + 1, type);
        perimeter += visitAdjacentPlot(area, plotPosition.row() - 1, plotPosition.col(), type);
        perimeter += visitAdjacentPlot(area, plotPosition.row() + 1, plotPosition.col(), type);

        return perimeter;
    }

    private int visitAdjacentPlot(AtomicInteger area, int row, int col, char type) {
        var position = new Position(row, col);
        if (!position.isOnMap(farmDimensions) || getType(position) != type) {
            return 1;
        } else if (!visitedPlots.contains(position)) {
            visitedPlots.add(position);
            area.incrementAndGet();
            return visit(position, area);
        }

        return 0;
    }

    private char getType(Position position) {
        return farm[position.row()][position.col()];
    }
}
