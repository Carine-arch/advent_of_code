package main.java.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public static List<String> getLinesFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

}
