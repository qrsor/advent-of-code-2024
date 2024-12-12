package pl.qrsor.adventofcode2024.day11;

import java.math.BigInteger;
import java.util.Optional;

class Stone {
    private BigInteger engraving;

    public Stone(BigInteger engraving) {
        this.engraving = engraving;
    }

    public Optional<Stone> transform() {
        if (engraving.equals(BigInteger.ZERO)) {
            engraving = BigInteger.ONE;
            return Optional.empty();
        } else {
            var engravingString = String.valueOf(engraving);
            int engravingLength = engravingString.length();
            if (engravingLength % 2 == 0) {

                engraving = new BigInteger(engravingString.substring(0, engravingLength / 2));
                return Optional.of(new Stone(new BigInteger(engravingString.substring(engravingLength / 2))));
            }
        }

        engraving = BigInteger.valueOf(2024).multiply(engraving);
        return Optional.empty();
    }

    public BigInteger engraving() {
        return engraving;
    }
}
