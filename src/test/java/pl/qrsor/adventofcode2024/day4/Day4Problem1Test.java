package pl.qrsor.adventofcode2024.day4;

import org.junit.jupiter.api.Test;
import pl.qrsor.adventofcode2024.CharMatrixInput;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Problem1Test {
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
        var result = new Day4Problem1().countXmas(CharMatrixInput.parseStrings(input));

        //then
        assertEquals(18, result);
    }
}