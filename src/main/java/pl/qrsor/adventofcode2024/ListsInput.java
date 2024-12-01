package pl.qrsor.adventofcode2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ListsInput
{
	static ListPair readInput(String filePath) {
		var rawData = readFile(filePath);
		return parseRawData(rawData);
	}

	private static ListPair parseRawData(List<String> rawData)
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

	private static List<String> readFile(String filePath)
	{
		var data = new ArrayList<String>();

		File resourceFile = new File(Objects.requireNonNull(Day1Problem1.class.getClassLoader().getResource(filePath)).getFile());

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
			System.out.println("Failed to read file" + e.getMessage());
		}

		return data;
	}
}