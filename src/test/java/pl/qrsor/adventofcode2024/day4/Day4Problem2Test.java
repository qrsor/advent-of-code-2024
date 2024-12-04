package pl.qrsor.adventofcode2024.day4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Problem2Test {
    @Test
    void shouldCheckSafeReportCount() {
        /// given
        var input = List.of("MMMSXXMASM",
                "MSAMXMSMSA",
                "AMXSXMAAMM",
                "MSAMASMSMX",
                "XMASAMXAMM",
                "XXAMMXXAMA",
                "SMSMSASXSS",
                "SAXAMASAAA",
                "MAMMMXMMMM",
                "MXMXAXMASX");

        //when
        var result = new Day4Problem2().countXmas(CharMatrixInput.parseStrings(input));

        //then
        assertEquals(9, result);
    }
}