package main.java.day_8;

import main.resources.puzzle_input.PuzzleInput_22_8;

import java.util.List;

public class TreeService {

    public void resolve() {
        int[][] treePatch = transformInputToMatrice();
        countVisibleTree(treePatch);
        findBestSpotScore(treePatch);

        // part 1 result = 1807
        // part 2 result = 480000
    }

    private int[][] transformInputToMatrice() {
        List<String> input = new PuzzleInput_22_8().getPuzzleInput();
        int[][] matrice = new int[input.size()][input.get(0).length()];

        for (int lineIndex = 0; lineIndex < input.size(); lineIndex++) {// line
            String line = input.get(lineIndex);
            for (int colIndex = 0; colIndex < line.length(); colIndex++) {//column
                matrice[lineIndex][colIndex] = Integer.parseInt(line.split("")[colIndex]);
            }
        }
        return matrice;
    }

    private void countVisibleTree(int[][] treePatch) {
        int rowLength = treePatch.length;
        int colLength = treePatch[0].length;
        int visibleTrees = colLength * 2 + rowLength * 2 - 4; // init valeur avec le compte des arbres sur le côté qui sont tous visibles

        // eviter de séparer par cas particuliers et tout faire ensemble
        for (int i = 1; i < rowLength - 1; i++) { // de 2eme ligne à avant dernière ligne
            for (int j = 1; j < colLength - 1; j++) { // de 2eme colonne à avant dernière colonne
                int tree = treePatch[i][j];
                if (tree > maxBottom(treePatch, j, i) || tree > maxUp(treePatch, j, i) || tree > maxLeft(treePatch, j, i) || tree > maxRight(treePatch, j, i)) {
                    visibleTrees++;
                }
            }
        }

        System.out.println("visibleTrees: " + visibleTrees);
    }

    private static int maxBottom(int[][] treePatch, int colIndex, int rowIndex) {
        int max = treePatch[rowIndex + 1][colIndex];
        for (int i = rowIndex + 1; i < treePatch[0].length; ++i) {
            max = Math.max(max, treePatch[i][colIndex]);
        }
        return max;
    }

    private static int maxUp(int[][] treePatch, int colIndex, int rowIndex) {
        int max = treePatch[0][colIndex];
        for (int i = 0; i < rowIndex; ++i) {
            max = Math.max(max, treePatch[i][colIndex]);
        }
        return max;
    }

    private static int maxLeft(int[][] treePatch, int colIndex, int rowIndex) {
        int max = treePatch[rowIndex][0];
        for (int j = 0; j < colIndex; ++j) {
            max = Math.max(max, treePatch[rowIndex][j]);
        }
        return max;
    }

    private static int maxRight(int[][] treePatch, int colIndex, int rowIndex) {
        int max = treePatch[rowIndex][colIndex + 1];
        for (int j = colIndex + 1; j < treePatch.length; ++j) {
            max = Math.max(max, treePatch[rowIndex][j]);
        }
        return max;
    }

    private void findBestSpotScore(int[][] treePatch) {
        int rowLength = treePatch.length;
        int colLength = treePatch[0].length;
        int bestScore = 0;

        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int colIndex = 0; colIndex < colLength; colIndex++) {
                int score = calculateScenicScore(rowIndex, colIndex, treePatch);
                if (score > bestScore) {
                    bestScore = score;
                }
            }
        }
        System.out.println("bestScore: " + bestScore);
    }

    private int calculateScenicScore(int rowIndex, int colIndex, int[][] treePatch) {
        if (rowIndex == 0 || colIndex == 0 || rowIndex == treePatch.length - 1 || colIndex == treePatch[0].length - 1) {
            return 0;
        }
        return numberTreeLeft(rowIndex, colIndex, treePatch) * numberTreeBottom(rowIndex, colIndex, treePatch)
                * numberTreeRight(rowIndex, colIndex, treePatch) * numberTreeUp(rowIndex, colIndex, treePatch);
    }

    private int numberTreeUp(int rowIndex, int colIndex, int[][] treePatch) {
        if (rowIndex == 0) {
            return 0;
        }
        int numberUp = 0;
        for (int i = rowIndex; i > 0; i--) {
            if (treePatch[rowIndex][colIndex] > treePatch[i - 1][colIndex]) {
                numberUp++;
            } else {
                numberUp++;
                break;
            }
        }
        return numberUp;
    }

    private int numberTreeBottom(int rowIndex, int colIndex, int[][] treePatch) {
        int rowLength = treePatch.length - 1;
        if (rowIndex == rowLength) {
            return 0;
        }
        int numberBottom = 0;
        for (int i = rowIndex; i < rowLength; i++) {
            if (treePatch[rowIndex][colIndex] > treePatch[i + 1][colIndex]) {
                numberBottom++;
            } else {
                numberBottom++;
                break;
            }
        }
        return numberBottom;
    }

    private int numberTreeLeft(int rowIndex, int colIndex, int[][] treePatch) {
        if (colIndex == 0) {
            return 0;
        }
        int numberLeft = 0;
        for (int j = colIndex; j > 0; j--) {
            if (treePatch[rowIndex][colIndex] > treePatch[rowIndex][j - 1]) {
                numberLeft++;
            } else {
                numberLeft++;
                break;
            }
        }
        return numberLeft;
    }

    private int numberTreeRight(int rowIndex, int colIndex, int[][] treePatch) {
        int colLength = treePatch[0].length - 1;
        if (colIndex == colLength) {
            return 0;
        }
        int numberRight = 0;
        for (int j = colIndex; j < colLength; j++) {
            if (treePatch[rowIndex][colIndex] > treePatch[rowIndex][j + 1]) {
                numberRight++;
            } else {
                numberRight++;
                break;
            }
        }
        return numberRight;
    }
}
