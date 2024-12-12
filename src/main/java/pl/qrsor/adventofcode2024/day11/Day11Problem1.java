package pl.qrsor.adventofcode2024.day11;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Day11Problem1 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day11Problem1().solve("0 27 5409930 828979 4471 3 68524 170", 25));
    }

    int countStones(List<Stone> stones, int blinkCount) {
        var observer = new StoneObserver(stones);
        IntStream.range(0, 75).forEach(i -> observer.blink());

        return observer.observedStoneCount();
    }

    int solve(String stoneString, int blinkCount) {
        var stones = Arrays.stream(stoneString.split(" "))
                .map(BigInteger::new)
                .map(pl.qrsor.adventofcode2024.day11.Stone::new)
                .toList();
        return countStones(stones, blinkCount);
    }


}
