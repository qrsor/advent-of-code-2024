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

class WideWarehouseRobotTest {

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
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^"), new Position(1, 4)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^^"), new Position(1, 4)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^<"), new Position(1, 3)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^>>"), new Position(1, 6)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^>>>"), new Position(1, 7)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^>>>>"), new Position(1, 8)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^>>>>>"), new Position(1, 9)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^>>>>>>"), new Position(1, 9)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^>>>>>>>"), new Position(1, 9)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("^>>>>v"), new Position(2, 8)),
                Arguments.of(convertTextBlockTo2DCharArray(WAREHOUSE_1), convertStringToMoveDirections("<^^>>>vv<v>>v<<"), new Position(5, 6)),
                Arguments.of(convertTextBlockTo2DCharArray("""
                        ########
                        #......#
                        ##.....#
                        #....O.#
                        #.#@OO.#
                        #...OOO#
                        #......#
                        ########
                        """), convertStringToMoveDirections(">>^^>>v"), new Position(3, 10)),
                Arguments.of(convertTextBlockTo2DCharArray("""
                        ########
                        #......#
                        #......#
                        #...O..#
                        #...OO.#
                        #.@OOO.#
                        #..#...#
                        ########
                        """), convertStringToMoveDirections(">>^^>>^>vvvvv"), new Position(2, 9))
        );
    }

    @ParameterizedTest
    @MethodSource("warehouseRobotTest")
    void shouldMoveRobotAccordingToDirection(char[][] warehouse, List<Direction> moveDirections, Position expectedPosition) {
        //given
        var underTest = new WideWarehouseRobot(warehouse);

        //when
        var result = moveDirections.stream().map(underTest::walk).toList().getLast();
        underTest.boxes();

        //then
        assertThat(result).isEqualTo(expectedPosition);
    }

    @Test
    void shouldMoveBoxInWarehouse() {
        //given
        var warehouse = convertTextBlockTo2DCharArray("""
                ##########
                #..O..O.O#
                #......O.#
                #.OO..O.O#
                #..O@..O.#
                #O#..O...#
                #O..O..O.#
                #.OO.O.OO#
                #....O...#
                ##########
                """);
        var underTest = new WideWarehouseRobot(warehouse);

        String moveDirectionsString = "<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^" +
                "vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v" +
                "><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<" +
                "<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^" +
                "^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><" +
                "^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^" +
                ">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^" +
                "<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>" +
                "^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>" +
                "v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^";
        var moveDirections = moveDirectionsString.chars()
                .mapToObj(i -> (char) i)
                .map(c -> switch (c) {
                    case '^' -> Direction.UP;
                    case '>' -> Direction.RIGHT;
                    case 'v' -> Direction.DOWN;
                    case '<' -> Direction.LEFT;
                    default -> throw new IllegalStateException("Unexpected value: " + c);
                }).toList();

        //when
        var result = moveDirections.stream().map(underTest::walk).toList().getLast();
        var gps = underTest.boxes().stream()
                .mapToInt(box -> 100 * box.row() + box.col()).sum();

        //then
        assertThat(result).isEqualTo(new Position(7, 4));
        assertThat(gps).isEqualTo(9021);
    }
}