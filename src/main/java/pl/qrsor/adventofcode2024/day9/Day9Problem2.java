package pl.qrsor.adventofcode2024.day9;

import java.math.BigInteger;

class Day9Problem2 {

    // 7511623485352 too high
    // 7511592939870 too high

    public static void main(String[] args) {
        System.out.println("Result " + new Day9Problem2().solve("day9-problem1-input"));
    }

    BigInteger calculateFilesystemChecksum(String input) {
        var diskMap = new DiskMap2(input);
        return diskMap.checksum();
    }

    BigInteger solve(String filePath) {
        return calculateFilesystemChecksum(StringInput.readInput(filePath));
    }


}
