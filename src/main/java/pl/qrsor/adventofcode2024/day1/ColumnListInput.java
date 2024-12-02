package pl.qrsor.adventofcode2024.day1;

import pl.qrsor.adventofcode2024.Input;

import java.util.ArrayList;
import java.util.List;


public class ColumnListInput
{
	static ListPair readInput(String filePath) {
		var rawData = Input.readFile(filePath);
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

}