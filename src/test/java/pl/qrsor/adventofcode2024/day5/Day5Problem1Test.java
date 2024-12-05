package pl.qrsor.adventofcode2024.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Problem1Test {
    @Test
    void shouldCheckSafeReportCount() {
        //when
        var result = new Day5Problem1().solve("day5-problem1-input.csv");

        //then
        assertEquals(143, result);
    }
}