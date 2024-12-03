package pl.qrsor.adventofcode2024.day2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Problem2Test {
    @Test
    void shouldCheckSafeReportCount() {
        //when
        var result = new Day2Problem2().solve("day2-problem1-input.csv");

        //then
        assertEquals(4, result);
    }

    public static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(List.of(0), -1),
                Arguments.of(List.of(0, 3), -1),
                Arguments.of(List.of(0, 4), 1),
                Arguments.of(List.of(0, 0), 1),
                Arguments.of(List.of(0, 3, 2), 2)

        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void shouldCheckListIsMonotonicWithSmallGaps(List<Integer> list, int expected) {
        //when
        var result = Day2Problem2.getViolationIndexOfMonotonicListWithLimitedGap(list, true);

        //then
        assertEquals(expected, result);
    }
}