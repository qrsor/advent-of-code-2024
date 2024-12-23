package pl.qrsor.adventofcode2024.day12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Problem2Test {

    public static Stream<Arguments> castCalculationArguments() {
        return Stream.of(
                Arguments.of("""
                        A
                        """, 4),
                Arguments.of("""
                        AB
                        """, 8),
                Arguments.of("""
                        AA
                        """, 8),
                Arguments.of("""
                        AA
                        AB
                        """, 6 * 3 + 4),
                Arguments.of("""
                        BA
                        AA
                        """, 6 * 3 + 4),
                Arguments.of("""
                        AAB
                        CAA
                        """, 4 + 4 + 8 * 4),
                Arguments.of("""
                        AAAA
                        BBCD
                        BBCC
                        EEEC
                        """, 80),
                Arguments.of("""
                        EEEEE
                        EXXXX
                        EEEEE
                        EXXXX
                        EEEEE
                        """, 236),
                Arguments.of("""
                        AAAAAA
                        AAABBA
                        AAABBA
                        ABBAAA
                        ABBAAA
                        AAAAAA
                        """, 368),
                Arguments.of("""
                        AAA
                        AAB
                        ABA
                        AAA
                        """, 4 + 4 + 12 * 10),
                Arguments.of("""
                        AAAA
                        AABA
                        ABAA
                        AAAA
                        """, 4 + 4 + 12 * 14),
                Arguments.of("""
                        AAA
                        ABA
                        AAA
                        """, 4 + 8 * 8),
                Arguments.of("""
                        RRRRIICCFF
                        RRRRIICCCF
                        VVRRRCCFFF
                        VVRCCCJFFF
                        VVVVCJJCFE
                        VVIVCCJJEE
                        VVIIICJJEE
                        MIIIIIJJEE
                        MIIISIJEEE
                        MMMISSJEEE
                        """, 1206)
        );
    }

    @ParameterizedTest
    @MethodSource("castCalculationArguments")
    void shouldCalculateFenceCostConsideringSidesAndArea(String input, int expectedCost) {
        //given
        var farm = Arrays.stream(input.split("\n"))
                .map(String::toCharArray)
                .toList()
                .toArray(new char[0][]);

        //when
        int result = new Day12Problem2().calculateFencePrice(farm);

        //then
        assertThat(result).isEqualTo(expectedCost);
    }

    @Test
    void shouldCalculateFenceCostConsideringSidesAndAreaDebug() {
        //given
//        String input = """
//                AAAAAA
//                AAABBA
//                AAABBA
//                ABBAAA
//                ABBAAA
//                AAAAAA
//                """;
//        var expectedCost = 368;
        String input = """
                AAAA
                AABA
                ABAA
                AAAA
                """;
        int expectedCost = 4 + 4 + 14 * 12;

        var farm = Arrays.stream(input.split("\n"))
                .map(String::toCharArray)
                .toList()
                .toArray(new char[0][]);

        //when
        int result = new Day12Problem2().calculateFencePrice(farm);

        //then
        assertThat(result).isEqualTo(expectedCost);
    }
}