package year_24.day_8;

import utils.Matrice;
import utils.Position;
import utils.PuzzleInputUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EasterEggService {

    final static String EMPTY = ".";
    final static String ANTINODE = "#";

    public int part1(String fileName) {
        List<String> puzzleInputTest = PuzzleInputUtils.getLinesFromFile(fileName);

        // créer une matrice avec le puzzle
        Matrice matrice = PuzzleInputUtils.transformInputToMatrice(puzzleInputTest);

        // récupérer dans la matrice toutes les positions qui ont une antenne
        List<Position> antennaPositions = matrice.positions().stream().filter(position -> !position.value().equals(EMPTY)).toList();
        // les trier par fréquence
        Map<String, List<Position>> groupedFrequency = groupAntennaPositionsByValue(antennaPositions);

        // list des antinodes
        Set<Position> antinodes = new HashSet<>();
        // pour chaque fréquence :
        // pour chaque position de la fréquence je calcule la différence en x et y entre la position 1 et 2
        // je reporte cette différence dans une nouvelle position à partir de la position 2
        groupedFrequency.values().forEach(positions -> {
            positions.forEach(position -> {
                // je compare chaque position avec toutes les autres positions
                positions.forEach(position2 -> {
                    if (!position.equals(position2)) {
                        Position antinodePosition = getAntinodePosition(position, position2);
                        if (matrice.getPosition(antinodePosition.rowIndex(), antinodePosition.colIndex()) != null) {
                            antinodes.add(antinodePosition);
                        }
                    }
                });
            });
        });

        return antinodes.size();
    }

    public int part2(String fileName) {
        List<String> puzzleInputTest = PuzzleInputUtils.getLinesFromFile(fileName);

        // créer une matrice avec le puzzle
        Matrice matrice = PuzzleInputUtils.transformInputToMatrice(puzzleInputTest);

        // récupérer dans la matrice toutes les positions qui ont une antenne
        List<Position> antennaPositions = matrice.positions().stream().filter(position -> !position.value().equals(EMPTY)).toList();
        // les trier par fréquence
        Map<String, List<Position>> groupedFrequency = groupAntennaPositionsByValue(antennaPositions);

        // list des antinodes
        Set<Position> antinodes = new HashSet<>();
        // pour chaque fréquence :
        // pour chaque position de la fréquence je calcule la différence en x et y entre la position 1 et 2
        // je reporte cette différence dans une nouvelle position à partir de la position 2
        groupedFrequency.values().forEach(positions -> {
            positions.forEach(position -> {
                // j'ajoute la position de l'antenne en lui donnant une valeur d'antinode pour le set
                antinodes.add(new Position(position.rowIndex(), position.colIndex(), ANTINODE));
                // je compare chaque position avec toutes les autres positions
                positions.forEach(position2 -> {
                    if (!position.equals(position2)) {
                        Set<Position> antinodePositions = getRecursiveAntinodePosition(matrice, position, position2, new HashSet<>());
                        antinodes.addAll(antinodePositions);
                    }
                });
            });
        });

        return antinodes.size();
    }

    public Map<String, List<Position>> groupAntennaPositionsByValue(List<Position> antennaPositions) {
        // Construire la Map où la clé est la "value()" et la valeur est la liste des "Position"
        Map<String, List<Position>> groupedByValue = antennaPositions.stream()
                .collect(Collectors.groupingBy(Position::value));

        // Afficher ou utiliser la Map selon vos besoins
        groupedByValue.forEach((key, positions) -> {
            System.out.println("Value: " + key + " Positions: " + positions);
        });
        return groupedByValue;
    }

    private Position getAntinodePosition(Position position1, Position position2) {
        int diffRowPosition = position2.rowIndex() - position1.rowIndex();
        int diffColPosition = position2.colIndex() - position1.colIndex();
        return new Position(position2.rowIndex() + diffRowPosition,
                position2.colIndex() + diffColPosition,
                ANTINODE
        );
    }

    private Set<Position> getRecursiveAntinodePosition(Matrice matrice, Position position1, Position position2, Set<Position> antinodes) {
        Position antinodePosition = getAntinodePosition(position1, position2);
        // si mon antinode est hors limite de la matrice je termine la récursivité et renvoie la liste
        if (matrice.getPosition(antinodePosition.rowIndex(), antinodePosition.colIndex()) == null) {
            return antinodes;
        }
        // sinon j'ajoute la position de l'antinode à mon set et je calcule la position de l'antinode suivant
        antinodes.add(antinodePosition);
        return getRecursiveAntinodePosition(matrice, position2, antinodePosition, antinodes);
    }
}
