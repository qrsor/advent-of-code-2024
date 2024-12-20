package pl.qrsor.adventofcode2024.day9;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class DiskMap3 {

    private final List<Integer> diskMap = new ArrayList<>();

    public DiskMap3(String input) {
        IntStream.range(0, input.length()).forEach(i -> {
            var number = Character.getNumericValue(input.charAt(i));
            var id = i % 2 == 0 ? i / 2 : -1;
            IntStream.range(0, number).forEach((int j) -> diskMap.add(id));
        });

//        System.out.println(this);
        defragment();
//        System.out.println(this);
    }

    private void defragment() {
        var leftPointer = 0;
        var rightPointer = diskMap.size() - 1;


        while (leftPointer < rightPointer) {

//            System.out.println(" ".repeat(rightPointer) + "R");
//            System.out.println(this);
//            System.out.println(" ".repeat(leftPointer) + "L");

            var leftNum = diskMap.get(leftPointer);
            var rightNum = diskMap.get(rightPointer);

            var rightBlockSize = calculateRightBlockSize(rightPointer, rightNum);
            if (leftNum > 0 && rightNum > 0 && rightPointer - leftPointer == 1) {
                rightPointer -= rightBlockSize;
                leftPointer = 0;
            } else if (leftNum >= 0) {
                leftPointer++;
            } else if (rightNum < 0) {
                rightPointer--;
            } else {
                if (hasEnoughFreeSpace(leftPointer, rightBlockSize)) {
                    for (int i = 0; i < rightBlockSize; i++) {
                        diskMap.set(leftPointer, rightNum);
                        diskMap.set(rightPointer, leftNum);
                        leftPointer++;
                        rightPointer--;
                    }
                    leftPointer = 0;
                } else {
                    leftPointer++;
                }
            }
        }
    }

    private boolean hasEnoughFreeSpace(int leftPointer, int rightBlockSize) {
        return IntStream.range(0, rightBlockSize).map(i -> diskMap.get(leftPointer + i)).allMatch(j -> j == -1);
    }

    private int calculateRightBlockSize(int rightPointer, int rightNum) {
        var result = rightPointer;

        var num = diskMap.get(rightPointer);
        while (num == rightNum && result > 0) {
            result--;
            num = diskMap.get(result);
        }
        return rightPointer - result;
    }

    BigInteger checksum() {
        BigInteger b = BigInteger.ZERO;

        for (int i = 0; i < diskMap.size(); i++) {
            Integer id = diskMap.get(i);
            var value = id >= 0 ? i * id : 0;

            b = b.add(BigInteger.valueOf(value));
        }
        return b;
    }

    @Override
    public String toString() {
        return diskMap.stream().map(id -> id.equals(-1) ? "." : id.toString()).collect(Collectors.joining());
//        return diskMap.stream().map(id -> id.equals(-1) ? "." : "[" + id + "]").collect(Collectors.joining());
    }
}
