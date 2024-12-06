package pl.qrsor.adventofcode2024.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Problem1Test {
    @Test
    void shouldPassSampleTest() {
        //when
        var result = new Day6Problem1().solve("day6-problem1-input");

        //then
        assertEquals(41, result);
    }
}