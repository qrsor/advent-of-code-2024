package pl.qrsor.adventofcode2024.day12;

import pl.qrsor.adventofcode2024.CharMatrixInput;
import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Day12Problem2 {

    private final Set<Position> visitedPlots = new HashSet<Position>();
    private Dimensions farmDimensions;
    private char[][] farm;

    // 848786 too high
    // 821372
    public static void main(String[] args) {
        System.out.println("Result " + new Day12Problem2().solve("day12-problem1-input-1"));
    }

    public int solve(String filePath) {
        return calculateFencePrice(CharMatrixInput.readInput(filePath));
    }

    public int calculateFencePrice(char[][] farm) {

        this.farm = farm;
        farmDimensions = new Dimensions(farm.length, farm[0].length);

        var result = 0;

        for (int row = 0; row < farmDimensions.rowCount(); row++) {
            for (int col = 0; col < farmDimensions.colCount(); col++) {
                var plotPosition = new Position(row, col);
                if (!visitedPlots.contains(plotPosition)) {
                    AtomicInteger area = new AtomicInteger(0);
                    HashSet<Position> multiCorners = new HashSet<>();
                    var cornerCount = visit(plotPosition, area, multiCorners);
                    result += area.get() * cornerCount;
                    System.out.printf("Multicorners %s\n", multiCorners);
                    System.out.printf("Area %d; corners: %d\n", area.get(), cornerCount);
                }
            }
        }

        return result;
    }

    private int visit(Position plot, AtomicInteger area, Set<Position> multiCorners) {
        visitedPlots.add(plot);

        area.incrementAndGet();
        var localCornerCount = countCorners(plot, multiCorners);

        var type = getType(plot);

        var cornerCount = plot.neighbours(farmDimensions)
                .stream()
                .filter(neighbour -> getType(neighbour) == type)
                .filter(Predicate.not(visitedPlots::contains))
                .mapToInt(neighbour -> visit(neighbour, area, multiCorners))
                .sum();

        return cornerCount + localCornerCount;
    }

    private int countCorners(Position plot, Set<Position> multiCorners) {
        var result = 0;

        if (ifCorner(plot, Direction.UP, Direction.LEFT, multiCorners)) {
            result++;
        }

        if (ifCorner(plot, Direction.UP, Direction.RIGHT, multiCorners)) {
            result++;
        }

        if (ifCorner(plot, Direction.DOWN, Direction.LEFT, multiCorners)) {
            result++;
        }

        if (ifCorner(plot, Direction.DOWN, Direction.RIGHT, multiCorners)) {
            result++;
        }

        return result;
    }

    private boolean ifCorner(Position plot, Direction shift1, Direction shift2, Set<Position> multiCorners) {

        var posDir1 = plot.neighbour(shift1);
        var posDir2 = plot.neighbour(shift2);
        var posDiag = posDir1.neighbour(shift2);

        var type = getType(plot);
        /*
         * xx
         * xA
         */
        var isOffMapOrDifferentType = Stream.of(posDir1, posDir2, posDiag)
                .allMatch(pos -> pos.isOffMap(farmDimensions) || getType(pos) != type);

        if (isOffMapOrDifferentType) {
            return true;
        }

        /*
         * Ax
         * xA
         */
        var isDiagSameType = getType(posDir1) != type && getType(posDir2) != type && getType(posDiag) == type;
        if (isDiagSameType) {
            return true;
        }

        /*
         * xA    AA     Ax
         * AA OR xA OR  AA
         */
        var case1 = getType(posDir1) == type && getType(posDir2) == type && getType(posDiag) != type;
        var case2 = getType(posDir1) == type && getType(posDir2) != type && getType(posDiag) == type;
        var case3 = getType(posDir1) != type && getType(posDir2) == type && getType(posDiag) == type;

        boolean checkNotInMultiCorners = !(multiCorners.contains(posDir1) || multiCorners.contains(posDir2) || multiCorners.contains(posDiag));
        if ((case1 || case2 || case3) && checkNotInMultiCorners) {
            multiCorners.add(plot);
            return true;
        }

        return false;
    }

    private char getType(Position position) {
        try {
            return farm[position.row()][position.col()];
        } catch (IndexOutOfBoundsException e) {
            return '1';
        }
    }
}
