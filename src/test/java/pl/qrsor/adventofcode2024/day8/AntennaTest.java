package pl.qrsor.adventofcode2024.day8;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AntennaTest {

    @Test
    void shouldNotResonateGivenDifferentFrequencies() {
        //given
        var antennaA = new Antenna('X', new Position(0, 0));
        var antennaB = new Antenna('Y', new Position(1, 1));
        Dimensions mapDimensions = new Dimensions(2, 2);

        //when
        var result = antennaA.resonates(antennaB, mapDimensions, 1);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldResonateGivenAntennasHorizontally() {
        //given
        var antennaA = new Antenna('X', new Position(0, 1));
        var antennaB = new Antenna('X', new Position(0, 2));
        Dimensions mapDimensions = new Dimensions(4, 5);

        //when
        var result = antennaA.resonates(antennaB, mapDimensions, 1);
        var result2 = antennaB.resonates(antennaA, mapDimensions, 1);
        var result3 = antennaB.resonates(antennaA, mapDimensions, 10);

        //then
        Antinode expectedAntinode1 = new Antinode(new Position(0, 0));
        Antinode expectedAntinode2 = new Antinode(new Position(0, 3));
        assertThat(result).hasSameElementsAs(Set.of(expectedAntinode1, expectedAntinode2));
        assertThat(result2).hasSameElementsAs(Set.of(expectedAntinode1, expectedAntinode2));
        assertThat(result3).hasSameElementsAs(Set.of(expectedAntinode1, expectedAntinode2,
                new Antinode(new Position(0, 4)),
                new Antinode(new Position(0, 1)),
                new Antinode(new Position(0, 2))

        ));
    }

    @Test
    void shouldResonateGivenAntennasHorizontallyOnEdge() {
        //given
        var antennaA = new Antenna('X', new Position(0, 1));
        var antennaB = new Antenna('X', new Position(0, 2));
        Dimensions mapDimensions = new Dimensions(4, 3);

        //when
        var result = antennaA.resonates(antennaB, mapDimensions, 1);
        var result2 = antennaB.resonates(antennaA, mapDimensions, 1);

        //then
        Antinode expectedAntinode1 = new Antinode(new Position(0, 0));
        assertThat(result).hasSameElementsAs(Set.of(expectedAntinode1));
        assertThat(result2).hasSameElementsAs(Set.of(expectedAntinode1));
    }

    @Test
    void shouldResonateGivenAntennasVertically() {
        //given
        var antennaA = new Antenna('X', new Position(1, 0));
        var antennaB = new Antenna('X', new Position(2, 0));
        Dimensions mapDimensions = new Dimensions(4, 4);

        //when
        var result = antennaA.resonates(antennaB, mapDimensions, 1);
        var result2 = antennaB.resonates(antennaA, mapDimensions, 1);

        //then
        Antinode expectedAntinode1 = new Antinode(new Position(0, 0));
        Antinode expectedAntinode2 = new Antinode(new Position(3, 0));
        assertThat(result).hasSameElementsAs(Set.of(expectedAntinode1, expectedAntinode2));
        assertThat(result2).hasSameElementsAs(Set.of(expectedAntinode1, expectedAntinode2));
    }

    @Test
    void shouldResonateGivenAntennasVerticallyOnEdge() {
        //given
        var antennaA = new Antenna('X', new Position(1, 0));
        var antennaB = new Antenna('X', new Position(2, 0));
        Dimensions mapDimensions = new Dimensions(3, 4);

        //when
        var result = antennaA.resonates(antennaB, mapDimensions, 1);
        var result2 = antennaB.resonates(antennaA, mapDimensions, 1);

        //then
        Antinode expectedAntinode1 = new Antinode(new Position(0, 0));
        assertThat(result).hasSameElementsAs(Set.of(expectedAntinode1));
        assertThat(result2).hasSameElementsAs(Set.of(expectedAntinode1));
    }

    @Test
    void shouldResonateGivenAntennasDiagonalRight() {
        //given
        var antennaA = new Antenna('X', new Position(1, 1));
        var antennaB = new Antenna('X', new Position(2, 2));
        Dimensions mapDimensions = new Dimensions(4, 4);

        //when
        var result = antennaA.resonates(antennaB, mapDimensions, 1);
        var result2 = antennaB.resonates(antennaA, mapDimensions, 1);

        //then
        Antinode expectedAntinode1 = new Antinode(new Position(0, 0));
        Antinode expectedAntinode2 = new Antinode(new Position(3, 3));
        assertThat(result).hasSameElementsAs(Set.of(expectedAntinode1, expectedAntinode2));
        assertThat(result2).hasSameElementsAs(Set.of(expectedAntinode1, expectedAntinode2));
    }

    @Test
    void shouldResonateGivenAntennasDiagonalRightOnEdge() {
        //given
        var antennaA = new Antenna('X', new Position(1, 1));
        var antennaB = new Antenna('X', new Position(2, 2));
        Dimensions mapDimensions = new Dimensions(3, 3);

        //when
        var result = antennaA.resonates(antennaB, mapDimensions, 1);
        var result2 = antennaB.resonates(antennaA, mapDimensions, 1);

        //then
        Antinode expectedAntinode1 = new Antinode(new Position(0, 0));
        assertThat(result).hasSameElementsAs(Set.of(expectedAntinode1));
        assertThat(result2).hasSameElementsAs(Set.of(expectedAntinode1));
    }

    @Test
    void shouldResonateGivenAntennasDiagonalLeft() {
        //given
        var antennaA = new Antenna('X', new Position(1, 2));
        var antennaB = new Antenna('X', new Position(2, 1));
        Dimensions mapDimensions = new Dimensions(4, 4);

        //when
        var result = antennaA.resonates(antennaB, mapDimensions, 1);
        var result2 = antennaB.resonates(antennaA, mapDimensions, 1);

        //then
        Antinode expectedAntinode1 = new Antinode(new Position(0, 3));
        Antinode expectedAntinode2 = new Antinode(new Position(3, 0));
        assertThat(result).hasSameElementsAs(Set.of(expectedAntinode1, expectedAntinode2));
        assertThat(result2).hasSameElementsAs(Set.of(expectedAntinode1, expectedAntinode2));
    }

    @Test
    void shouldResonateGivenAntennasDiagonalLeftOnEdge() {
        //given
        var antennaA = new Antenna('X', new Position(1, 2));
        var antennaB = new Antenna('X', new Position(2, 1));
        Dimensions mapDimensions = new Dimensions(4, 3);

        //when
        var result = antennaA.resonates(antennaB, mapDimensions, 1);
        var result2 = antennaB.resonates(antennaA, mapDimensions, 1);

        //then
        Antinode expectedAntinode1 = new Antinode(new Position(3, 0));
        assertThat(result).hasSameElementsAs(Set.of(expectedAntinode1));
        assertThat(result2).hasSameElementsAs(Set.of(expectedAntinode1));
    }
}