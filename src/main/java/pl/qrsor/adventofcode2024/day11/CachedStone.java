package pl.qrsor.adventofcode2024.day11;

import java.math.BigInteger;
import java.util.Map;

public class CachedStone {

    private static final BigInteger MULTIPLIER = BigInteger.valueOf(2024);
    private final Map<Pair, BigInteger> knownStones;

    public CachedStone(Map<Pair, BigInteger> knownStones) {
        this.knownStones = knownStones;
    }

    BigInteger calculateStoneValue(BigInteger stone, int blinksLeft) {
        Pair key = new Pair(stone, blinksLeft);
        if (knownStones.containsKey(key)) {
            return knownStones.get(key);
        } else {
            var value = calculateStoneValueForCache(key);

            knownStones.put(key, value);

            return value;
        }
    }

    private BigInteger calculateStoneValueForCache(Pair pair) {
        var stone = pair.stone();
        var blinkCount = pair.blinkCount();

        if (blinkCount == 0) {
            return BigInteger.ONE;
        }

        if (stone.equals(BigInteger.ZERO)) {
            return calculateStoneValue(BigInteger.ONE, blinkCount - 1);
        } else {
            var stoneString = stone.toString();
            int stoneLength = stoneString.length();
            if (stoneLength % 2 == 0) {

                var leftValue = calculateStoneValue(new BigInteger(stoneString.substring(0, stoneLength / 2)), blinkCount - 1);
                var rightValue = calculateStoneValue(new BigInteger(stoneString.substring(stoneLength / 2)), blinkCount - 1);
                return leftValue.add(rightValue);
            }

        }

        return calculateStoneValue(MULTIPLIER.multiply(stone), blinkCount - 1);
    }

}
