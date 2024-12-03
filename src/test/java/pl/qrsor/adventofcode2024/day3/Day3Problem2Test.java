package pl.qrsor.adventofcode2024.day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Problem2Test {
    @Test
    void shouldCheckSafeReportCount() {
        /// given
        var input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";

        //when
        var result = new Day3Problem2().validMultiplications(List.of(input));

        //then
        assertEquals(48, result);
    }
}

//174561379
//111972528