package pl.qrsor.adventofcode2024.day6;

import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.Set;

class LoopingGuard {

    private final Set<Footstep> walkedPositions = new HashSet<>();
    private final Set<Position> obstacles;
    private final Dimensions mapDimensions;
    private final Position startPosition;
    private Position extraObstacle;
    private Position position;
    private Direction direction = Direction.UP;

    LoopingGuard(char[][] map) {
        this.mapDimensions = new Dimensions(map.length, map[0].length);

        this.obstacles = this.analyzeMap(map);

        this.startPosition = position;

        try {
            markPositionWalked();
        } catch (WalkingInCirclesException e) {
            throw new RuntimeException(e); //impossibru
        }
    }

    LoopingGuard(Set<Position> obstacles, Position extraObstacle, Position startPosition, Dimensions mapDimensions) {
        this.obstacles = obstacles;
        this.extraObstacle = extraObstacle;
        this.startPosition = startPosition;
        this.mapDimensions = mapDimensions;
        this.position = startPosition;

        try {
            markPositionWalked();
        } catch (WalkingInCirclesException e) {
            throw new RuntimeException(e); //impossibru
        }
    }

    private Set<Position> analyzeMap(char[][] map) {

        var result = new HashSet<Position>();

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                char tile = map[row][column];
                if (tile == '#') {
                    result.add(new Position(row, column));
                } else if (tile == '^') {
                    this.position = new Position(row, column);
                }
            }
        }
        return result;
    }

    private void markPositionWalked() throws WalkingInCirclesException {

//        drawDebug();

        Footstep currentFootstep = new Footstep(position, direction);
        if (walkedPositions.contains(currentFootstep)) {
            throw new WalkingInCirclesException(currentFootstep);
        }

        walkedPositions.add(currentFootstep);
    }

    void walk() throws CannotWalkException, WalkingInCirclesException {
//        drawDebug();
        var nextPosition = calculateNextPosition();

        if (obstacles.contains(nextPosition) || nextPosition.equals(extraObstacle)) {
            turn();
        } else {
            position = nextPosition;
        }

        if (position.isOffMap(mapDimensions)) {
            throw new CannotWalkException();
        }

        markPositionWalked();
    }

    private Position calculateNextPosition() {
        return switch (direction) {
            case UP -> new Position(position.row() - 1, position.col());
            case RIGHT -> new Position(position.row(), position.col() + 1);
            case DOWN -> new Position(position.row() + 1, position.col());
            case LEFT -> new Position(position.row(), position.col() - 1);
        };
    }

    void turn() {
        direction = switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }

    Set<Footstep> traceFootsteps() {
        return walkedPositions;
    }

    Position startedAt() {
        return startPosition;
    }

    private void drawDebug() {
        for (int i = 0; i < mapDimensions.rowCount(); i++) {
            for (int j = 0; j < mapDimensions.colCount(); j++) {
                Position drawnPosition = new Position(i, j);
                if (drawnPosition.equals(extraObstacle)) {
                    System.out.print("0");
                } else if (obstacles.contains(drawnPosition)) {
                    System.out.print("#");
                } else if (position.equals(drawnPosition)) {
                    System.out.print(switch (direction) {
                        case UP -> "^";
                        case RIGHT -> ">";
                        case DOWN -> "v";
                        case LEFT -> "<";
                    });
                } else if (walkedPositions.stream().map(Footstep::position).anyMatch(position -> position.equals(drawnPosition))) {
                    System.out.print("x");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

    public Set<Position> obstacles() {
        return obstacles;
    }

    public Dimensions mapDimensions() {
        return mapDimensions;
    }
}
