package pl.qrsor.adventofcode2024.day12;

import pl.qrsor.adventofcode2024.CharMatrixInput;
import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Day12Problem1 {

    public int solve(String filePath) {
        return calculateFencePrice(CharMatrixInput.readInput(filePath));
    }

    private int calculateFencePrice(char[][] farm) {

        var visitedPlots = new HashSet<Plot>();

        var result = 0;

        var farmDimensions = new Dimensions(farm.length, farm[0].length);

        for (int row = 0; row < farmDimensions.rowCount(); row++) {
            for (int col = 0; col < farmDimensions.colCount(); col++) {
                Plot plot = new Plot(new Position(row, col), farmDimensions, visitedPlots, farm, farm[row][col]);
                var areaAndPerimeter = plot.countAreaAndPerimeter(1);

                result += areaAndPerimeter.get().area() * areaAndPerimeter.get().perimeter();
            }
        }

        return result;
    }

    static class Plot {

        private final Position position;
        private final Dimensions farmDimensions;
        private final Set<Plot> visitedPlots;
        private final char[][] farm;
        private final char type;

        Plot(Position position, Dimensions farmDimensions, Set<Plot> visitedPlots, char[][] farm, char type) {
            this.position = position;
            this.farmDimensions = farmDimensions;
            this.visitedPlots = visitedPlots;
            this.farm = farm;
            this.type = type;
        }

        public Optional<AreaAndPerimeter> countAreaAndPerimeter(int area) {
            if (visitedPlots.contains(this)) {
                return Optional.empty();
            }

            visitedPlots.add(this);

            Optional<AreaAndPerimeter> left = getAreaAndPerimeter(area, position.row(), position().col() - 1);
            Optional<AreaAndPerimeter> right = getAreaAndPerimeter(area, position.row(), position().col() + 1);
            Optional<AreaAndPerimeter> top = getAreaAndPerimeter(area, position.row() - 1, position().col());
            Optional<AreaAndPerimeter> down = getAreaAndPerimeter(area, position.row() + 1, position().col());

            var rperimeter = 0;
            var rarea = 0;
            if (left.isPresent()) {
                rperimeter += left.get().perimeter();
                rarea += left.get().area();
            }
            if (right.isPresent()) {
                rperimeter += right.get().perimeter();
                rarea += right.get().area();
            }
            if (top.isPresent()) {
                rperimeter += top.get().perimeter();
                rarea += top.get().area();
            }
            if (down.isPresent()) {
                rperimeter += down.get().perimeter();
                rarea += left.get().area();
            }


            return Optional.of(new AreaAndPerimeter(rarea, rperimeter));
        }

        private Position position() {
            return position;
        }

        private Optional<AreaAndPerimeter> getAreaAndPerimeter(int area, int row, int col) {
            var position = new Position(row, col);
            if (position.isOnMap(farmDimensions)) {
                char type = farm[position.row()][position.col()];
                if (type == this.type) {
                    var plot = new Plot(position, farmDimensions, visitedPlots, farm, type);
                    return plot.countAreaAndPerimeter(area + 1);
                } else {
                    return Optional.empty();
                }

            } else {
                return Optional.of(new AreaAndPerimeter(area, 1));
            }
        }
    }
}
