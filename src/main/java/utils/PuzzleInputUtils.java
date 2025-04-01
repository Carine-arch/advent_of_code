package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleInputUtils {

    public static Matrice transformInputToMatrice(List<String> puzzleInput) {

        List<Position> positions = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < puzzleInput.size(); lineIndex++) {// line
            String line = puzzleInput.get(lineIndex);
            for (int colIndex = 0; colIndex < line.length(); colIndex++) {//column
                positions.add(new Position(lineIndex, colIndex, line.split("")[colIndex]));
            }
        }

        return new Matrice(positions, puzzleInput.size(), puzzleInput.get(0).length());
    }

    public static int[][] transformInputToDoubleArray(List<String> puzzleInput) {

        int[][] matrice = new int[puzzleInput.size()][puzzleInput.get(0).length()];

        for (int lineIndex = 0; lineIndex < puzzleInput.size(); lineIndex++) {// line
            String line = puzzleInput.get(lineIndex);
            for (int colIndex = 0; colIndex < line.length(); colIndex++) {//column
                matrice[lineIndex][colIndex] = Integer.parseInt(line.split("")[colIndex]);
            }
        }

        return matrice;
    }

    public static List<String> getLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    //TODO: a tester
    public static String getStringFromFile(String filePath) {
        String firstLine = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                firstLine = line;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return firstLine;
    }

}
