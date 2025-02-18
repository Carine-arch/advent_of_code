package utils;

import java.util.ArrayList;
import java.util.List;

public class PuzzleInputUtils {

    public Matrice transformInputToMatrice(List<String> puzzleInput) {

        List<Position> positions = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < puzzleInput.size(); lineIndex++) {// line
            String line = puzzleInput.get(lineIndex);
            for (int colIndex = 0; colIndex < line.length(); colIndex++) {//column
                positions.add(new Position(lineIndex, colIndex, line.split("")[colIndex]));
            }
        }

        return new Matrice(positions, puzzleInput.size(), puzzleInput.get(0).length());
    }
}
