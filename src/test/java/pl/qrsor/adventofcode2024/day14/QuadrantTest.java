package pl.qrsor.adventofcode2024.day14;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QuadrantTest {

    public static Stream<Arguments> quantifyPositionsSource() {
        return Stream.of(
                Arguments.of(new Position(1, 0), new Dimensions(3, 3), 1),
                Arguments.of(new Position(0, 0), new Dimensions(3, 3), 2),
                Arguments.of(new Position(0, 2), new Dimensions(3, 3), 2),
                Arguments.of(new Position(0, 1), new Dimensions(3, 3), 1),
                Arguments.of(new Position(0, 2), new Dimensions(3, 3), 2)
        );
    }

    @ParameterizedTest
    @MethodSource("quantifyPositionsSource")
    void shouldQuantifyPositions(Position position, Dimensions dimensions, int expectedFactor) {
        //given
        var underTest = new Quadrant(dimensions);
        underTest.quantify(new Position(0, 0));
        underTest.quantify(new Position(0, 2));
        underTest.quantify(new Position(2, 0));
        underTest.quantify(new Position(2, 2));

        //when
        underTest.quantify(position);
        var result = underTest.safeFactor();

        //then
        assertThat(result).isEqualTo(expectedFactor);
    }
}