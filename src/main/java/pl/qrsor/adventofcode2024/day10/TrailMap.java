package pl.qrsor.adventofcode2024.day10;

import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrailMap {

    private final Map<Integer, Set<Position>> tiles = new HashMap<>();
    private final Dimensions mapDimensions;
    private final Set<TrailTile> trails = new HashSet<>();

    public TrailMap(char[][] map) {
        this.mapDimensions = new Dimensions(map.length, map[0].length);
        this.analyzeMap(map);
        this.findTrails();
    }

    public TrailMap(String input) {
        this(Arrays.stream(input.split("\n"))
                .map(String::toCharArray)
                .toList()
                .toArray(new char[0][]));
    }

    private void findTrails() {
        this.trails.addAll(tiles.get(9).stream().map(tile9 -> new TrailTile(tile9, findNextPosition(8, tile9))).collect(Collectors.toSet()));
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
        if (height < 0) {
            return;
        }
        var position = calculatePosition(centerPosition, direction);
        if (position.isOnMap(mapDimensions)) {
            var heightTiles = tiles.get(height);
            if (heightTiles.contains(position)) {
                var nextSteps = findNextPosition(height - 1, position);
                TrailTile newTile = new TrailTile(position, nextSteps);
                result.add(newTile);
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
            var visited = new HashSet<TrailTile>();
            visit(trail, visited);
            return visited.stream()
                    .map(TrailTile::position)
                    .filter(tiles.get(0)::contains)
                    .distinct()
                    .count();
        }).sum();
    }

    private void visit(TrailTile trail, HashSet<TrailTile> visited) {
        visited.add(trail);
        trail.next().forEach(tile -> {
            if (!visited.contains(tile)) {
                visit(tile, visited);
            }
        });
    }
}
