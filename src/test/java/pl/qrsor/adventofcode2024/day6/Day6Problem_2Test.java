package pl.qrsor.adventofcode2024.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Problem_2Test {
    @Test
    void shouldPassSampleTest1() {
        //when
        var result = new Day6Problem2().solve("day6-problem1-input1");

        //then
        assertEquals(6, result);
    }

    @Test
    void shouldPassSampleTest2() {
        //when
        var result = new Day6Problem2().solve("day6-problem1-input2");

        //then
        assertEquals(0, result);
    }

    @Test
    void shouldPassSampleTest3() {
        //when
        var result = new Day6Problem2().solve("day6-problem1-input3");

        //then
        assertEquals(2, result);
    }

    @Test
    void shouldPassSampleTest4() {
        //when
        var result = new Day6Problem2().solve("day6-problem1-input4");

        //then
        assertEquals(0, result);
    }

    @Test
    void shouldPassSampleTest5() {
        //when
        var result = new Day6Problem2().solve("day6-problem1-input5");

        //then
        assertEquals(3, result);
    }
}