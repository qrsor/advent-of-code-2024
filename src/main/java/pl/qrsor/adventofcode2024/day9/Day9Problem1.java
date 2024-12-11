package pl.qrsor.adventofcode2024.day9;

import java.math.BigInteger;

class Day9Problem1 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day9Problem1().solve("day9-problem1-input"));
    }

    BigInteger calculateFilesystemChecksum(String input) {
        DiskMap diskMap = new DiskMap(input);
        return diskMap.checksum();
    }

    BigInteger solve(String filePath) {
        return calculateFilesystemChecksum(StringInput.readInput(filePath));
    }


}
