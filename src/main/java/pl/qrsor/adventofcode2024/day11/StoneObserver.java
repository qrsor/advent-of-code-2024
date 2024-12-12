package pl.qrsor.adventofcode2024.day11;

import java.util.ArrayList;
import java.util.List;

public class StoneObserver {
    private final List<Stone> stoneLine = new ArrayList<>();

    public StoneObserver(List<Stone> stoneLine) {
        this.stoneLine.addAll(stoneLine);
    }

    public void blink() {
        var newStone = new ArrayList<Stone>();
        for (int i = 0; i < stoneLine.size(); i++) {
            var currentStone = stoneLine.get(i);
            var maybeNewStone = currentStone.transform();
            maybeNewStone.ifPresent(newStone::add);
        }

        stoneLine.addAll(newStone);
    }

    public int observedStoneCount() {
        return stoneLine.size();
    }
}
