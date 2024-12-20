package pl.qrsor.adventofcode2024.day9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Problem2Test {

    // 2333133121414131402 2858
    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            input,                  expected
            111,                    1
            121,                    1
            12101,                  4
            112,                    5
            12211,                  9
            13302,                  21
            121211313,              241
            """)
    void shouldCalculateChecksum(String input, int expected) {
        //when
        var result = new Day9Problem2().calculateFilesystemChecksum(input);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldCalculateChecksum2() {
        //given
        var input = "2333133121414131402";
        var expected = 2858;

        //when
        var result = new Day9Problem2().calculateFilesystemChecksum(input);

        //then
        assertThat(result).isEqualTo(expected);
    }
}