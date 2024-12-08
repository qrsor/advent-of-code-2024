package pl.qrsor.adventofcode2024.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Problem1Test {
    @Test
    void shouldPassSampleTest() {
        //when
        var result = new Day8Problem1().solve("day8-problem1-input");

        //then
        assertEquals(14, result);
    }
}