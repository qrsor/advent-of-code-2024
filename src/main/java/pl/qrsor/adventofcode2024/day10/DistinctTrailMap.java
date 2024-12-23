package pl.qrsor.adventofcode2024.day10;

import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DistinctTrailMap {

    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
    // Declaring the color
    // Custom declaration
    public static final String ANSI_YELLOW = "\u001B[33m";
    private final Map<Integer, Set<Position>> tiles = new HashMap<>();
    private final Dimensions mapDimensions;
    private final Set<TrailTile> trails = new HashSet<>();
    private final char[][] map;

    public DistinctTrailMap(char[][] map) {
        this.map = map;
        this.mapDimensions = new Dimensions(map.length, map[0].length);
        this.analyzeMap(map);
        this.findTrails();
    }

    public DistinctTrailMap(String input) {
        this(Arrays.stream(input.split("\n"))
                .map(String::toCharArray)
                .toList()
                .toArray(new char[0][]));
    }

    private void findTrails() {
        this.trails.addAll(tiles.get(0).stream().map(tile0 -> {
            Set<TrailTile> nextPosition = findNextPosition(1, tile0);
            if (nextPosition.isEmpty()) {
                return null;
            }
            return new TrailTile(tile0, nextPosition);
        }).filter(Objects::nonNull).collect(Collectors.toSet()));
    }

    private Set<TrailTile> findNextPosition(int height, Position centerPosition) {

        var result = new HashSet<TrailTile>();

        getTrailTile(height, centerPosition, result, Direction.UP);
        getTrailTile(height, centerPosition, result, Direction.DOWN);
        getTrailTile(height, centerPosition, result, Direction.LEFT);
        getTrailTile(height, centerPosition, result, Direction.RIGHT);

        return result;
    }

    private void getTrailTile(int height, Position centerPosition, HashSet<TrailTile> result, Direction direction) {
        if (height > 9) {
            return;
        }
        var position = calculatePosition(centerPosition, direction);
        if (position.isOnMap(mapDimensions)) {
            var heightTiles = tiles.get(height);
            if (heightTiles.contains(position)) {
                var nextSteps = findNextPosition(height + 1, position);
                if (!nextSteps.isEmpty() || height == 9) {
                    TrailTile newTile = new TrailTile(position, nextSteps);
                    result.add(newTile);
                }
            }
        }
    }

    private Position calculatePosition(Position position, Direction direction) {
        return switch (direction) {
            case UP -> new Position(position.row() - 1, position.col());
            case RIGHT -> new Position(position.row(), position.col() + 1);
            case DOWN -> new Position(position.row() + 1, position.col());
            case LEFT -> new Position(position.row(), position.col() - 1);
        };
    }

    private void analyzeMap(char[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                var height = Character.getNumericValue(map[row][col]);
                var position = new Position(row, col);
                if (tiles.containsKey(height)) {
                    tiles.get(height).add(position);
                } else {
                    tiles.put(height, Stream.of(position).collect(Collectors.toSet()));
                }
            }
        }
    }

    long trailCount() {

        // visit all trails and count unique zero height
        return trails.stream().mapToLong(trail -> {
            var paths = new AtomicInteger(0);
            visit(trail, paths);
            return paths.get();
        }).sum();
    }

    private void visit(TrailTile trail, AtomicInteger paths) {

        if (trail.next().isEmpty()) {
            paths.incrementAndGet();
        }

        trail.next().forEach(tile -> {
            visit(tile, paths);
        });
    }

    private void draw(ArrayList<TrailTile> visited) {
        for (int i = 0; i < mapDimensions.rowCount(); i++) {
            for (int j = 0; j < mapDimensions.colCount(); j++) {
                var pos = new Position(i, j);
                if (visited.stream().map(TrailTile::position).collect(Collectors.toSet()).contains(pos)) {
                    // Printing the text on console prior adding
                    // the desired color
                    System.out.print(ANSI_YELLOW
                            + map[i][j]
                            + ANSI_RESET);
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void draw(TrailTile current) {
        for (int i = 0; i < mapDimensions.rowCount(); i++) {
            for (int j = 0; j < mapDimensions.colCount(); j++) {
                var pos = new Position(i, j);
                if (current.position().equals(pos)) {
                    // Printing the text on console prior adding
                    // the desired color
                    System.out.print(ANSI_YELLOW
                            + map[i][j]
                            + ANSI_RESET);
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
