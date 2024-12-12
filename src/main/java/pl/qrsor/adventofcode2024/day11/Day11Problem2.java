package pl.qrsor.adventofcode2024.day11;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Day11Problem2 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day11Problem2().solve("0 27 5409930 828979 4471 3 68524 170", 75));
    }

    BigInteger countStones(List<BigInteger> stones, int blinkCount) {
        Map<Pair, BigInteger> knownStones = new HashMap<>(50000);
        var cachedStone = new CachedStone(knownStones);
        return stones.stream().map(stone -> cachedStone.calculateStoneValue(stone, blinkCount)).reduce(BigInteger::add).get();
    }

    BigInteger solve(String stoneString, int blinkCount) {
        var stones = Arrays.stream(stoneString.split(" "))
                .map(BigInteger::new)
                .toList();
        return countStones(stones, blinkCount);
    }
}
