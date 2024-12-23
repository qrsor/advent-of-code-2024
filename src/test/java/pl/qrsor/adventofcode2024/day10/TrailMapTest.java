package pl.qrsor.adventofcode2024.day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TrailMapTest {
    public static Stream<Arguments> trailsParams() {
        return Stream.of(
                Arguments.of("""
                        0123
                        1234
                        8765
                        9876
                        """, 1),
                Arguments.of("""
                        ...0...
                        ...1...
                        ...2...
                        6543456
                        7.....7
                        8.....8
                        9.....9
                        """, 2),
                Arguments.of("""
                        ..90..9
                        ...1.98
                        ...2..7
                        6543456
                        765.987
                        876....
                        987....
                        """, 4),
                Arguments.of("""
                        10..9..
                        2...8..
                        3...7..
                        4567654
                        ...8..3
                        ...9..2
                        .....01
                        """, 3),
                Arguments.of("""
                        89010123
                        78121874
                        87430965
                        96549874
                        45678903
                        32019012
                        01329801
                        10456732
                        """, 36)
        );
    }

    @ParameterizedTest
    @MethodSource("trailsParams")
    void shouldCountTrails(String map, long expectedCount) {
        //given

        //when
        var trailMap = new TrailMap(map);

        //then
        assertThat(trailMap.trailCount()).isEqualTo(expectedCount);
    }
}