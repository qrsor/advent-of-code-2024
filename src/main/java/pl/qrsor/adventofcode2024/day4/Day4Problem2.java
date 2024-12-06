package pl.qrsor.adventofcode2024.day4;

import pl.qrsor.adventofcode2024.CharMatrixInput;

public class Day4Problem2 {
    private static final int SEARCH_WORD_LENGTH = 4;

    public static void main(String[] args) {
        System.out.println("Result " + new Day4Problem2().solve("day4-problem1-input.csv"));
    }

    public int countXmas(char[][] rows) {

        var result = 0;

        for (int i = 0; i < rows.length; i++) {
            var row = rows[i];
            // find start or end char;
            for (int j = 0; j < row.length; j++) {
                var letter = row[j];
                if (letter == 'A') {
                    if (checkX(rows, i, j, 'M', 'M', 'S', 'S')) {
                        result++;
                    } else if (checkX(rows, i, j, 'M', 'S', 'M', 'S')) {
                        result++;
                    } else if (checkX(rows, i, j, 'S', 'M', 'S', 'M')) {
                        result++;
                    } else if (checkX(rows, i, j, 'S', 'S', 'M', 'M')) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private boolean checkX(char[][] rows, int rowIndex, int aIndex, char topLeft, char topRight, char bottomLeft, char bottomRight) {
        var row = rows[rowIndex];
        if (aIndex == 0 || aIndex == row.length - 1 || rowIndex == 0 || rowIndex == rows.length - 1) { // won't fit in the array
            return false;
        }

        return topLeft == rows[rowIndex - 1][aIndex - 1]
                && topRight == rows[rowIndex - 1][aIndex + 1]
                && bottomLeft == rows[rowIndex + 1][aIndex - 1]
                && bottomRight == rows[rowIndex + 1][aIndex + 1];
    }

    public int solve(String filePath) {
        return countXmas(CharMatrixInput.readInput(filePath));
    }
}
