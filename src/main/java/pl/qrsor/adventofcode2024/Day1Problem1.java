package pl.qrsor.adventofcode2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


record ListPair(List<Integer> leftList, List<Integer> rightList)
{
}

public class Day1Problem1
{
	private int totalDistance(ListPair lists)
	{
		lists.leftList().sort(Comparator.naturalOrder());
		lists.rightList().sort(Comparator.naturalOrder());

		var result = 0;

		while (!lists.leftList().isEmpty())
		{
			result += Math.abs(lists.leftList().removeFirst() - lists.rightList().removeFirst());
		}

		return result;
	}

	public int solve(String filePath)
	{
		// read data
		List<String> rawData = readFile(filePath);

		// init lists
		var lists = parseRawData(rawData);

		// calculate result
		return totalDistance(lists);
	}

	private ListPair parseRawData(List<String> rawData)
	{
		var leftList = new ArrayList<Integer>();
		var rightList = new ArrayList<Integer>();

		rawData.forEach(line -> {
			var numbers = line.split(" {3}");

			leftList.add(Integer.valueOf(numbers[0]));
			rightList.add(Integer.valueOf(numbers[1]));
		});

		return new ListPair(leftList, rightList);
	}

	private List<String> readFile(String filePath)
	{
		var data = new ArrayList<String>();

		File resourceFile = new File(Day1Problem1.class.getClassLoader().getResource(filePath).getFile());

		try (BufferedReader br = new BufferedReader(new FileReader(resourceFile)))
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				data.add(line);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return data;
	}

	public static void main(String[] args)
	{
		System.out.println("Result " + new Day1Problem1().solve("day1-problem1-input.csv"));
	}
}
