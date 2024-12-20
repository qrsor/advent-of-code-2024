package pl.qrsor.adventofcode2024.day9;

import java.math.BigInteger;

class Day9Problem3 {

    // 7511623485352 too high
    // 7511592939870 too high
    // 7511592939870
    // 7702486886960
    // 8181350006060
    // 6511178035564
    // 16020681627350


    public static void main(String[] args) {
        System.out.println("Result " + new Day9Problem3().solve("day9-problem1-input"));
    }

    BigInteger calculateFilesystemChecksum(String input) {
        var diskMap = new DiskMap3(input);
        return diskMap.checksum();
    }

    BigInteger solve(String filePath) {
        return calculateFilesystemChecksum(StringInput.readInput(filePath));
    }


}
