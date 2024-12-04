package pl.qrsor.adventofcode2024.day4;

public class Day4Problem1 {

    private static final int SEARCH_WORD_LENGTH = 4;

    record FourLetters(char letter1, char letter2, char letter3, char letter4) {
    };


    public int countXmas(char[][] rows) {

        var letters = new FourLetters('X', 'M', 'A', 'S');
        var inverseLetters = new FourLetters('S', 'A', 'M', 'X');

        var result = 0;

        for (int i = 0; i < rows.length; i++) {
            var row = rows[i];
            // find start or end char;
            for (int j = 0; j < row.length; j++) {
                var letter = row[j];
                if (letter == letters.letter1()) {
                    // check horizontal right;
                    if (checkHorizontalRight(rows, i, j, letters)) {
                        result++;
                    }

                    // check diagonal down left
                    if (checkDiagonalDownLeft(rows, i, j, letters)) {
                        result++;
                    }

                    // check vertical down
                    if (checkVerticalDown(rows, i, j, letters)) {
                        result++;
                    }

                    // check diagonal down right
                    if (checkDiagonalDownRight(rows, i, j, letters)) {
                        result++;
                    }
                } else if (letter == inverseLetters.letter1()) {
                    // check horizontal right;
                    if (checkHorizontalRight(rows, i, j, inverseLetters)) {
                        result++;
                    }

                    // check diagonal down left
                    if (checkDiagonalDownLeft(rows, i, j, inverseLetters)) {
                        result++;
                    }

                    // check vertical down
                    if (checkVerticalDown(rows, i, j, inverseLetters)) {
                        result++;
                    }

                    // check diagonal down right
                    if (checkDiagonalDownRight(rows, i, j, inverseLetters)) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private boolean checkHorizontalRight(char[][] rows, int rowIndex, int startIndexInRow, FourLetters letters) {
        var row = rows[rowIndex];
        if (startIndexInRow > row.length - SEARCH_WORD_LENGTH) { // won't fit in the array
            return false;
        }

        return row[startIndexInRow + 1] == letters.letter2() &&
                row[startIndexInRow + 2] == letters.letter3() &&
                row[startIndexInRow + 3] == letters.letter4();
    }

    private boolean checkDiagonalDownLeft(char[][] rows, int rowIndex, int startIndexInRow, FourLetters letters) {
        if (startIndexInRow < SEARCH_WORD_LENGTH - 1) { // won't fit in the array
            return false;
        }

        if (rowIndex > rows.length - SEARCH_WORD_LENGTH) {
            return false;
        }

        return rows[rowIndex + 1][startIndexInRow - 1] == letters.letter2() &&
                rows[rowIndex + 2][startIndexInRow - 2] == letters.letter3() &&
                rows[rowIndex + 3][startIndexInRow - 3] == letters.letter4();
    }

    private boolean checkVerticalDown(char[][] rows, int rowIndex, int startIndexInRow, FourLetters letters) {
        if (rowIndex > rows.length - SEARCH_WORD_LENGTH) { // won't fit in the array
            return false;
        }

        return rows[rowIndex + 1][startIndexInRow] == letters.letter2() &&
                rows[rowIndex + 2][startIndexInRow] == letters.letter3() &&
                rows[rowIndex + 3][startIndexInRow] == letters.letter4();
    }

    private boolean checkDiagonalDownRight(char[][] rows, int rowIndex, int startIndexInRow, FourLetters letters) {
        var row = rows[rowIndex];
        if (startIndexInRow > row.length - SEARCH_WORD_LENGTH) { // won't fit in the array
            return false;
        }

        if (rowIndex > rows.length - SEARCH_WORD_LENGTH) {
            return false;
        }

        return rows[rowIndex + 1][startIndexInRow + 1] == letters.letter2() &&
                rows[rowIndex + 2][startIndexInRow + 2] == letters.letter3() &&
                rows[rowIndex + 3][startIndexInRow + 3] == letters.letter4();
    }

    public int solve(String filePath) {
        return countXmas(CharMatrixInput.readInput(filePath));
    }

    public static void main(String[] args) {
        System.out.println("Result " + new Day4Problem1().solve("day4-problem1-input.csv"));
    }
}
