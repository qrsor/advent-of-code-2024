package pl.qrsor.adventofcode2024.day5;

import pl.qrsor.adventofcode2024.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RulesAndUpdatesInput {
    static RulesAndUpdates readInput(String filePath) {
        List<String> rawLines = Input.readFile(filePath);

        return parseRawData(rawLines);
    }

    static RulesAndUpdates parseRawData(List<String> rawLines) {

        var processingRules = true;
        var rules = new ArrayList<Rule>();
        var updates = new ArrayList<List<Integer>>();

        for (String line : rawLines) {
            if (line.isBlank()) {
                processingRules = false;
            } else if (processingRules) {
                var numbers = line.split("\\|");
                rules.add(new Rule(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
            } else {
                updates.add(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
            }
        }

        return new RulesAndUpdates(rules, updates);
    }
}
