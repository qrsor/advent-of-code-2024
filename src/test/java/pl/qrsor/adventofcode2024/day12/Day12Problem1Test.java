package pl.qrsor.adventofcode2024.day12;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Problem1Test {

    @Test
    void shouldWork() {
        int result = new Day12Problem1().solve("day12-problem1-input-1");

        assertThat(result).isEqualTo(140);
    }
}