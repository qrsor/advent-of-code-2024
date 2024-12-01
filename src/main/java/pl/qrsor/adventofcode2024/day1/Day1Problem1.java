package pl.qrsor.adventofcode2024.day1;

import java.util.Comparator;


public class Day1Problem1
{
	private int totalDistance(ListPair lists)
	{
		lists.leftList().sort(Comparator.naturalOrder());
		lists.rightList().sort(Comparator.naturalOrder());

		return lists.leftList().stream().reduce(0, (result, element) -> result + Math.abs(element- lists.rightList().removeFirst()));
	}

	public int solve(String filePath)
	{
		return totalDistance(ListsInput.readInput(filePath));
	}

	public static void main(String[] args)
	{
		System.out.println("Result " + new Day1Problem1().solve("day1-problem1-input.csv"));
	}
}
