package pl.qrsor.adventofcode2024.day8;

import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

import java.util.HashSet;
import java.util.Set;

public class AntinodeLocator {

    int countAntinodes(char[][] map, int antinodesLimitPerDirection) {
        var antennas = analyzeMap(map);

        int rowCount = map.length;
        int colCount = map[0].length;
        var antinodes = locateAntinodes(antennas, new Dimensions(rowCount, colCount), antinodesLimitPerDirection);

        return antinodes.size();
    }

    private Set<Antenna> analyzeMap(char[][] map) {

        var antennas = new HashSet<Antenna>();

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                char tile = map[row][column];
                if (tile != '.') {
                    antennas.add(new Antenna(tile, new Position(row, column)));
                }
            }
        }

        return antennas;
    }

    Set<Antinode> locateAntinodes(Set<Antenna> antennas, Dimensions mapDimensions, int antinodesLimitPerDirection) {
        var antennasList = antennas.stream().toList();
        var result = new HashSet<Antinode>();
        for (int i = 0; i < antennas.size() - 1; i++) {
            for (int j = i + 1; j < antennas.size(); j++) {
                var antenna1 = antennasList.get(i);
                var antenna2 = antennasList.get(j);
                try {
                    result.addAll(antenna1.resonates(antenna2, mapDimensions, antinodesLimitPerDirection));
                } catch (CannotResonateWithItself e) {
                    System.out.println("Cannot resonate with itself " + antenna1);
                }
            }
        }
        return result;
    }
}
