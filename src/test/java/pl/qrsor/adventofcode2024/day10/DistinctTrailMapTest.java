package pl.qrsor.adventofcode2024.day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DistinctTrailMapTest {
    public static Stream<Arguments> trailsParams() {
        return Stream.of(
                Arguments.of("""
                        0123456789
                        """, 1)
                ,
                Arguments.of("""
                        01234....
                        ....56789
                        .9876....
                        """, 2)
                ,
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
                        """, 13),
                Arguments.of("""
                        .....0.
                        ..4321.
                        ..5..2.
                        ..6543.
                        ..7..4.
                        ..8765.
                        ..9....
                        """, 3),
                Arguments.of("""
                        012345
                        123456
                        234567
                        345678
                        4.6789
                        56789.
                        """, 227),
                Arguments.of("""
                        89010123
                        78121874
                        87430965
                        96549874
                        45678903
                        32019012
                        01329801
                        10456732
                        """, 81)
        );
    }

    @ParameterizedTest
    @MethodSource("trailsParams")
    void shouldCountTrails(String map, long expectedCount) {
        //given

        //when
        var trailMap = new DistinctTrailMap(map);

        //then
        assertThat(trailMap.trailCount()).isEqualTo(expectedCount);
    }
}