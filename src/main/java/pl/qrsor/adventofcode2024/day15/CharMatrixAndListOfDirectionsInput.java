package pl.qrsor.adventofcode2024.day15;

import pl.qrsor.adventofcode2024.Direction;
import pl.qrsor.adventofcode2024.Input;

import java.util.List;
import java.util.stream.Collectors;

public class CharMatrixAndListOfDirectionsInput {
    public static char[][] getCharMatrix(String filePath) {
        List<String> strings = Input.readFile(filePath);

        return parseStrings(strings.subList(0, 50));
    }

    public static List<Direction> getListOfDirections(String filePath) {
        List<String> strings = Input.readFile(filePath);

        return String.join("", strings.subList(51, strings.size()))
                .chars()
                .mapToObj(c -> (char) c)
                .map(dir -> switch (dir) {
                    case '^' -> Direction.UP;
                    case 'v' -> Direction.DOWN;
                    case '>' -> Direction.RIGHT;
                    case '<' -> Direction.LEFT;
                    default -> throw new IllegalArgumentException("Invalid direction: " + dir);
                })
                .collect(Collectors.toList());
    }

    public static char[][] parseStrings(List<String> strings) {
        return strings.stream().map(String::toCharArray).toList()
                .toArray(new char[0][]);
    }
}
