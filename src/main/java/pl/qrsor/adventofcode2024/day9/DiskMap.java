package pl.qrsor.adventofcode2024.day9;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class DiskMap {

    private final List<Integer> diskMap = new ArrayList<>();

    public DiskMap(String input) {
        IntStream.range(0, input.length()).forEach(i -> {
            var number = Character.getNumericValue(input.charAt(i));
            var id = i % 2 == 0 ? i / 2 : -1;
            IntStream.range(0, number).forEach((int j) -> diskMap.add(id));
        });

        System.out.println(diskMap);
        defragment();
        System.out.println(diskMap);
    }

    private void defragment() {
        var leftPointer = 0;
        var rightPointer = diskMap.size() - 1;
        while (leftPointer < rightPointer) {
            var leftNum = diskMap.get(leftPointer);
            var rightNum = diskMap.get(rightPointer);
            if (leftNum >= 0) {
                leftPointer++;
            } else if (rightNum < 0) {
                rightPointer--;
            } else {
                diskMap.set(leftPointer, rightNum);
                diskMap.set(rightPointer, leftNum);
                leftPointer++;
                rightPointer--;
            }
        }
    }

    BigInteger checksum() {
        BigInteger b = BigInteger.ZERO;

        for (int i = 0; i < diskMap.size(); i++) {
            Integer id = diskMap.get(i);
            var value = id > 0 ? i * id : 0;

            b = b.add(BigInteger.valueOf(value));
        }
        return b;
    }
}
