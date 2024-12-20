package pl.qrsor.adventofcode2024.day9;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class DiskMap2 {

    private final List<File> diskMap = new ArrayList<>();

    public DiskMap2(String input) {
        IntStream.range(0, input.length()).forEach(i -> {
            var number = Character.getNumericValue(input.charAt(i));
            var id = i % 2 == 0 ? i / 2 : -1;

            if (number > 0) {
                diskMap.add(new File(id, number));
            }
        });

//        System.out.println(diskMap);
        defragment();
//        System.out.println(diskMap);
    }

    private void defragment() {
        var leftPointer = 0;
        var rightPointer = diskMap.size() - 1;
        while (leftPointer < rightPointer) { // Inspect the while

//            System.out.println(this);
//            System.out.println(" ".repeat(rightPointer) + "R");
//            IntStream.range(0, diskMap.size()).forEach(i -> System.out.print(i % 10));


//            System.out.println(" ".repeat(leftPointer) + "L");

            var leftFile = diskMap.get(leftPointer);
            var rightFile = diskMap.get(rightPointer);

//            System.out.println("L:" + leftPointer + "; P:" + rightPointer + "L:" + leftFile + "P: " + rightFile);

            if (leftFile.hasData()) {
                leftPointer++;
            } else if (rightFile.isFreeSpace()) {
                rightPointer--;
            } else if (!rightFile.checkCanMove()) {
                rightPointer--;
            } else {
                if (leftFile.size() > rightFile.size()) {
                    diskMap.set(rightPointer, new File(-1, rightFile.size()));
                    diskMap.add(leftPointer, rightFile);
                    leftFile.shrinkBy(rightFile);
                } else if (leftFile.size() == rightFile.size()) {
                    diskMap.set(leftPointer, rightFile);
                    diskMap.set(rightPointer, leftFile);
                    leftPointer++;
                } else {
                    leftPointer++;
                }
            }

            if (leftPointer == rightPointer) {
                rightFile.cannotMove();
                rightPointer--;
                leftPointer = 0;
            }
        }
    }

    BigInteger checksum() {
        BigInteger b = BigInteger.ZERO;

        var position = 0;
        for (int i = 0; i < diskMap.size(); i++) {
            var file = diskMap.get(i);
            var id = file.id();

            if (file.hasData()) {
                var value = IntStream.range(position, position + file.size()).sum();
                b = b.add(BigInteger.valueOf((long) value * id));
            }

            position += file.size();
        }
        return b;
    }

    @Override
    public String toString() {
        String dots = diskMap.stream().map(file -> {
            if (file.isFreeSpace()) {
                return ".".repeat(file.size());
            } else {
                return Integer.valueOf(file.id()).toString().repeat(file.size());
            }
        }).collect(Collectors.joining());
        return dots + "\n" + IntStream.range(0, dots.length()).map(i -> i % 10).mapToObj(Integer::toString).collect(Collectors.joining());
    }
}
