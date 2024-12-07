package pl.qrsor.adventofcode2024.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Problem2Test {
    @Test
    void shouldPassSampleTest() {
        //when
        var result = new Day7Problem2().solve("day7-problem1-input");

        //then
        assertEquals(11387, result);
    }
}