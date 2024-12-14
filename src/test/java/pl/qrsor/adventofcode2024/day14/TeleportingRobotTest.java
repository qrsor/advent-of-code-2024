package pl.qrsor.adventofcode2024.day14;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TeleportingRobotTest {

    public static Stream<Arguments> robotData() {
        return Stream.of(
                Arguments.of(new Position(0, 0), new Velocity(1, 1), new Dimensions(10, 10), 0, new Position(0, 0)),
                Arguments.of(new Position(0, 0), new Velocity(1, 1), new Dimensions(10, 10), 1, new Position(1, 1)),
                Arguments.of(new Position(0, 0), new Velocity(-1, -1), new Dimensions(10, 10), 1, new Position(9, 9)),
                Arguments.of(new Position(0, 0), new Velocity(-1, -1), new Dimensions(10, 10), 24, new Position(6, 6)),

                Arguments.of(new Position(4, 2), new Velocity(-3, 2), new Dimensions(7, 11), 0, new Position(4, 2)),
                Arguments.of(new Position(4, 2), new Velocity(-3, 2), new Dimensions(7, 11), 1, new Position(1, 4)),
                Arguments.of(new Position(4, 2), new Velocity(-3, 2), new Dimensions(7, 11), 2, new Position(5, 6)),
                Arguments.of(new Position(4, 2), new Velocity(-3, 2), new Dimensions(7, 11), 3, new Position(2, 8)),
                Arguments.of(new Position(4, 2), new Velocity(-3, 2), new Dimensions(7, 11), 4, new Position(6, 10)),
                Arguments.of(new Position(4, 2), new Velocity(-3, 2), new Dimensions(7, 11), 5, new Position(3, 1))

        );
    }

    @ParameterizedTest
    @MethodSource("robotData")
    void shouldTravelToExpectedTile(Position startPosition, Velocity velocity, Dimensions bathroomSpace, int travelTime, Position expectedPosition) {
        //given
        var underTest = new TeleportingRobot(startPosition, velocity, bathroomSpace);

        //when
        var endPosition = underTest.travelFor(travelTime);

        //then
        assertThat(endPosition).isEqualTo(expectedPosition);
    }
}