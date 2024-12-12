package pl.qrsor.adventofcode2024.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class CachedStoneTest {

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            stone,  blinks, expected
            0,      1,       1
            0,      2,      1
            0,      3,      2
            0,      4,      4
            """)
    void shouldClaculateStoneValue(BigInteger stone, int blinks, BigInteger expected) {
        var result = new CachedStone(new HashMap<>()).calculateStoneValue(stone, blinks);

        //then
        assertThat(result).isEqualTo(expected);
    }
}