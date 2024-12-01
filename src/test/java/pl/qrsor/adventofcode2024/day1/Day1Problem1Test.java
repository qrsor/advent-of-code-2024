package pl.qrsor.adventofcode2024.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class Day1Problem1Test
{
	@Test
	void shouldCalculateResult()
	{
		//when
		var result = new Day1Problem1().solve("day1-problem1-input.csv");

		//then
		assertEquals(11, result);
	}
}