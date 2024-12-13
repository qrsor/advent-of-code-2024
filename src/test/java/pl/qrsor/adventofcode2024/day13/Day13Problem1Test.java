package pl.qrsor.adventofcode2024.day13;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day13Problem1Test {
    @Test
    void shouldSolveSampleData() {
        var result = Day13Problem1.solveLinearSystem(new int[]{94, 22}, new int[]{34, 67}, 8400, 5400);

        assertThat(result[0]).isEqualTo(80);
        assertThat(result[1]).isEqualTo(40);
    }
}