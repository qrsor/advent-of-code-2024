package pl.qrsor.adventofcode2024.day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Problem1Test {
    @Test
    void shouldCheckSafeReportCount() {
        /// given
        var input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";

        //when
        var result = new Day3Problem1().validMultiplications(List.of(input));

        //then
        assertEquals(161, result);
    }
}