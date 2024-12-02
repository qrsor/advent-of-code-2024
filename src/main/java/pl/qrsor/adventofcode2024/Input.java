package pl.qrsor.adventofcode2024;

import pl.qrsor.adventofcode2024.day1.Day1Problem1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Input {
    public static List<String> readFile(String filePath) {
        var data = new ArrayList<String>();

        File resourceFile = new File(Objects.requireNonNull(Day1Problem1.class.getClassLoader().getResource(filePath)).getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(resourceFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read file" + e.getMessage());
        }

        return data;
    }
}
