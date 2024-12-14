package pl.qrsor.adventofcode2024.day14;

import pl.qrsor.adventofcode2024.Position;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day14Problem2 {

    public static void main(String[] args) {

        var bathRow = 103;
        var bathCol = 101;
        var travelTime = 10000;

        var plot = new char[103][101];
        init(plot);

        var robots = PositionVelocityReader.extract("day14-problem1-input", bathRow, bathCol);

        IntStream.range(0, travelTime).forEach(i -> {
            robots.forEach(robot -> {
                Position pos = robot.travelFor(1);

                draw(plot, pos);
            });

            if (distanceScore(robots, bathRow, bathCol) > (robots.size() - 150)) {

                print(plot);
                System.out.println(">>>" + (i + 1) + "<<<");
            }

            init(plot);
        });
    }

    private static long symmetryScore(List<TeleportingRobot> robots, int bathRow, int bathCol) {
        var positions = robots.stream()
                .map(TeleportingRobot::position)
                .collect(Collectors.toSet());

        long count = positions.stream()
                .map(position -> symetrical(position, bathCol / 2))
                .filter(positions::contains)
                .count();

//        System.out.println("score: " + count);

        return count;
    }

    private static long distanceScore(List<TeleportingRobot> robots, int bathRow, int bathCol) {
        var positions = robots.stream()
                .map(TeleportingRobot::position)
                .collect(Collectors.toSet());

        Set<Position> closePositions = positions.stream()
                .flatMap(Day14Problem2::close).collect(Collectors.toSet());
        long count = closePositions
                .stream()
                .filter(positions::contains)
                .count();

//        System.out.println("score: " + count);

        return count;
    }

    private static Stream<Position> close(Position position) {
        return Stream.of(
                new Position(position.row() - 1, position.col() - 1),
                new Position(position.row() - 1, position.col()),
                new Position(position.row() - 1, position.col() + 1),
                new Position(position.row(), position.col() - 1),
                new Position(position.row(), position.col() + 1),
                new Position(position.row() + 1, position.col() - 1),
                new Position(position.row() + 1, position.col()),
                new Position(position.row() + 1, position.col() + 1)
        );
    }

    private static Position symetrical(Position position, int colMedian) {

        var colSym = 2 * colMedian - position.col();

        return new Position(position.row(), colSym);
    }

    private static void print(char[][] plot) {
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[0].length; j++) {
                System.out.print(plot[i][j]);
            }
            System.out.println();
        }

        System.out.println("------------------------");
        init(plot);
    }

    private static void draw(char[][] plot, Position pos) {
        plot[pos.row()][pos.col()] = '*';
    }

    private static void init(char[][] plot) {
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[0].length; j++) {
                plot[i][j] = ' ';
            }
        }
    }

}
