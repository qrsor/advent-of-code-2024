package pl.qrsor.adventofcode2024.day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            ROW, COL, EXPECTED
            0,   0,   true
            -1,  0,   false
            0,   -1,  false
            0,   2,   false
            2,   0,   false
            1,   1,   true
            """)
    void shouldCheckIfPositionIsOnMap(int row, int col, boolean expected) {
        //given
        var mapDimensions = new Dimensions(2, 2);
        Position position = new Position(row, col);

        //when
        var result = position.isOnMap(mapDimensions);

        //then
        assertEquals(expected, result);
    }
}