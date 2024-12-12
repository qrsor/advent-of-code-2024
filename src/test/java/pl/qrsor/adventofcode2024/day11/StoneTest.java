package pl.qrsor.adventofcode2024.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class StoneTest {
    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            engraving,  expectedEngraving
            0,          1
            2,          4048
            3,          6072
            """)
    void shouldTransformStone(BigInteger engraving, BigInteger expectedEngraving) {
        //given
        var stone = new Stone(engraving);

        //when
        var result = stone.transform();

        //then
        assertThat(result).isEmpty();
        assertThat(stone.engraving()).isEqualTo(expectedEngraving);
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            engraving,  expectedEngraving,  expectedExtraEngraving
            10,         1,                  0
            22,         2,                  2
            3100,       31,                 0
            321002,     321,                2
            312102,     312,                102
            """)
    void shouldTransformStoneWithAdditionalStone(BigInteger engraving, BigInteger expectedEngraving, BigInteger expectedExtraEngraving) {
        //given
        var stone = new Stone(engraving);

        //when
        var result = stone.transform();

        //then
        assertThat(result).isNotEmpty();
        assertThat(result.get().engraving()).isEqualTo(expectedExtraEngraving);
        assertThat(stone.engraving()).isEqualTo(expectedEngraving);
    }


}