package pl.qrsor.adventofcode2024.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day1Problem2Test {
	@Test
	void shouldCalculateResult()
	{
		//when
		var result = new Day1Problem2().solve("day1-problem1-input.csv");

		//then
		assertEquals(31, result);
	}
}