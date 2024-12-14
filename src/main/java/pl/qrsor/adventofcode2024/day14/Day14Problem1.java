package pl.qrsor.adventofcode2024.day14;

import pl.qrsor.adventofcode2024.Dimensions;

public class Day14Problem1 {

    public static void main(String[] args) {

        var bathRow = 103;
        var bathCol = 101;
        var travelTime = 100;


        var robots = PositionVelocityReader.extract("day14-problem1-input", bathRow, bathCol);
        Quadrant quadrant = new Quadrant(new Dimensions(bathRow, bathCol));

        robots.stream()
                .map(robot -> robot.travelFor(travelTime))
                .forEach(quadrant::quantify);

        System.out.println("Result " + quadrant.safeFactor());
    }

}
