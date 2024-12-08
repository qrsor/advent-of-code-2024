package pl.qrsor.adventofcode2024.day8;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AntinodeLocatorTest {
    @Test
    void shouldLocateAntinodes() {
        //given
        var antennas = Set.of(
                new Antenna('B', new Position(0, 1)),
                new Antenna('B', new Position(0, 2)),
                new Antenna('A', new Position(1, 1)),
                new Antenna('A', new Position(2, 2)),
                new Antenna('C', new Position(1, 3)),
                new Antenna('C', new Position(2, 3))
        );

        //when
        var result = new AntinodeLocator().locateAntinodes(antennas, new Dimensions(4, 4));

        //then
        assertThat(result).containsOnly(
                new Antinode(new Position(0, 0)),
                new Antinode(new Position(0, 3)),
                new Antinode(new Position(3, 3))
        );
    }
}