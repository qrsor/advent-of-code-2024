package pl.qrsor.adventofcode2024.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Problem1Test {
    @Test
    void shouldCheckSafeReportCount() {
        //when
        var result = new Day2Problem1().solve("day2-problem1-input.csv");

        //then
        assertEquals(2, result);
    }
}