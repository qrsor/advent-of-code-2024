package pl.qrsor.adventofcode2024.day15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Position;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WarehouseRobotTest {

    public static char[][] convertTextBlockTo2DCharArray(String textBlock) {
        // Split the text block into lines
        String[] lines = textBlock.split("\n");

        // Create a 2D char array with the same dimensions as the text block
        char[][] charArray = new char[lines.length][];

        // Convert each line to a char array
        for (int i = 0; i < lines.length; i++) {
            charArray[i] = lines[i].toCharArray();
        }

        return charArray;
    }

    public static Stream<Arguments> warehouseRobotTest() {
        return Stream.of(
                Arguments.of(List.of(Direction.UP), new Position(1, 2)),
                Arguments.of(List.of(Direction.UP, Direction.UP), new Position(1, 2)),
                Arguments.of(List.of(Direction.UP, Direction.LEFT), new Position(1, 1)),
                Arguments.of(List.of(Direction.UP, Direction.RIGHT, Direction.RIGHT), new Position(1, 3))
        );
    }

    @ParameterizedTest
    @MethodSource("warehouseRobotTest")
    void shouldMoveRobotAccordingToDirection(List<Direction> moveDirections, Position expectedPosition) {
        //given
        var warehouse = convertTextBlockTo2DCharArray("""
                ########
                #..O.O.#
                ##@.O..#
                #...O..#
                #.#.O..#
                #...O..#
                #......#
                ########
                """);
        var underTest = new WarehouseRobot(warehouse);

        //when
        var result = moveDirections.stream().map(underTest::walk).toList().getLast();

        //then
        assertThat(result).isEqualTo(expectedPosition);
    }

    @Test
    void shouldMoveBoxInWarehouse() {
        //given
        var warehouse = convertTextBlockTo2DCharArray("""
                ########
                #..O.O.#
                ##@.O..#
                #...O..#
                #.#.O..#
                #...O..#
                #......#
                ########
                """);
        var underTest = new WarehouseRobot(warehouse);

        //when
        var result = Stream.of(Direction.UP, Direction.RIGHT, Direction.RIGHT).map(underTest::walk).toList().getLast();
        var boxes = underTest.boxes();

        //then
        assertThat(boxes).doesNotContain(new Position(1, 3));
        assertThat(boxes).contains(new Position(1, 4));
    }
}