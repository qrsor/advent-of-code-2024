package pl.qrsor.adventofcode2024.day9;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Problem1Test {
    @Test
    void shouldCalculateChecksum() {
        //when
        var result = new Day9Problem1().calculateFilesystemChecksum("2333133121414131402");

        //then
        assertThat(result).isEqualTo(1928);
    }
}