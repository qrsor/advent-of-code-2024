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

    private static final String WAREHOUSE_1 = """
            ########
            #..O.O.#
            ##@.O..#
            #...O..#
            #.#.O..#
            #...O..#
            #......#
            ########
            """;

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

    private static List<Direction> convertStringToMoveDirections(String moveDirectionsString) {
        return moveDirectionsString.chars()
                .mapToObj(i -> (char) i)
                .map(c -> switch (c) {
                    case '^' -> Direction.UP;
                    case '>' -> Direction.RIGHT;
                    case 'v' -> Direction.DOWN;
                    case '<' -> Direction.LEFT;
                    default -> throw new IllegalStateException("Unexpected value: " + c);
                }).toList();
    }


    public static Stream<Arguments> warehouseRobotTest() {
        return Stream.of(
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^"), new Position(1, 2)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^^"), new Position(1, 2)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^<"), new Position(1, 1)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^>>"), new Position(1, 4)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("<^^>>>vv<v>>v<<"), new Position(4, 4))
        );
    }

    @ParameterizedTest
    @MethodSource("warehouseRobotTest")
    void shouldMoveRobotAccordingToDirection(char[][] warehouse, List<Direction> moveDirections, Position expectedPosition) {
        //given
        var underTest = new WarehouseRobot(warehouse);

        //when
        var result = moveDirections.stream().map(underTest::walk).toList().getLast();

        //then
        assertThat(result).isEqualTo(expectedPosition);
    }

    @Test
    void shouldMoveBoxInWarehouse() {
        //given
        var warehouse = convertTextBlockTo2DCharArray(WAREHOUSE_1);
        var underTest = new WarehouseRobot(warehouse);

        //when
        var result = Stream.of(Direction.UP, Direction.RIGHT, Direction.RIGHT).map(underTest::walk).toList().getLast();
        var boxes = underTest.boxes();

        //then
        assertThat(result).isEqualTo(new Position(1, 4));
        assertThat(boxes).doesNotContain(new Position(1, 3));
        assertThat(boxes).contains(new Position(1, 4));
    }
}