package pl.qrsor.adventofcode2024.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Problem1Test {
    @Test
    void shouldPassSampleTest() {
        //when
        var result = new Day7Problem1().solve("day7-problem1-input");

        //then
        assertEquals(3749, result);
    }
}