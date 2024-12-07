package pl.qrsor.adventofcode2024.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Problem2Test {
    @Test
    void shouldPassSampleTest1() throws WalkingInCirclesException {
        //when
        var result = new Day6Problem2().solve("day6-problem1-input1");

        //then
        assertEquals(6, result);
    }

    @Test
    void shouldPassSampleTest2() throws WalkingInCirclesException {
        //when
        var result = new Day6Problem2().solve("day6-problem1-input2");

        //then
        assertEquals(0, result);
    }

    @Test
    void shouldPassSampleTest3() throws WalkingInCirclesException {
        //when
        var result = new Day6Problem2().solve("day6-problem1-input3");

        //then
        assertEquals(1, result);
    }
}