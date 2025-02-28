package main.java.year_24.day_4;

import main.resources.puzzle_input.PuzzleInput_24_4;

import java.util.List;

public class XMASServiceTest {

    private static final String XMAS = "XMAS";
    private static final Character X = 'X';
    private static final Character M = 'M';
    private static final Character A = 'A';
    private static final Character S = 'S';


    public void count() {
        Character[][] xmasMatrice = transformInputToMatrice();
        countXMAS(xmasMatrice);

        //2527 too low
        // result part 1 2557
    }

    private Character[][] transformInputToMatrice() {
        List<String> input = new PuzzleInput_24_4().getPuzzleInput().lines().toList();
        Character[][] matrice = new Character[input.size()][input.get(0).length()];

        for (int lineIndex = 0; lineIndex < input.size(); lineIndex++) {// line
            String line = input.get(lineIndex);
            for (int colIndex = 0; colIndex < line.length(); colIndex++) {//column
                matrice[lineIndex][colIndex] = line.toCharArray()[colIndex];
            }
        }
        return matrice;
    }


    private void countXMAS(Character[][] xmasMatrice) {
        int rowLength = xmasMatrice.length;
        int colLength = xmasMatrice[0].length;
        int numberXmas = 0;

        int countX = 0;
        for (int i = 0; i < rowLength; i++) { // ligne
            for (int j = 0; j < colLength; j++) { // colonne
                Character letter = xmasMatrice[i][j];
                if (letter.equals(X)) {
                    countX++;
                    System.out.println("letter: " + xmasMatrice[i][j] + "i: " + i + "j: " + j);
                    if (lookUp(xmasMatrice, i, j)) {
                        numberXmas++;
                        System.out.println(" up i: " + i + "j: " + j);
                    }
                    if (lookDown(xmasMatrice, i, j)) {
                        numberXmas++;
                        System.out.println(" down i: " + i + "j: " + j);

                    }
                    if (lookRight(xmasMatrice, i, j)) {
                        numberXmas++;
                        System.out.println("right i: " + i + "j: " + j);

                    }
                    if (lookLeft(xmasMatrice, i, j)) {
                        numberXmas++;
                        System.out.println("left i: " + i + "j: " + j);

                    }
                    if (lookDownRight(xmasMatrice, i, j)) {
                        numberXmas++;
                        System.out.println(" down r i: " + i + "j: " + j);

                    }
                    if (lookDownLeft(xmasMatrice, i, j)) {
                        numberXmas++;
                        System.out.println("down l i: " + i + "j: " + j);

                    }
                    if (lookUpRight(xmasMatrice, i, j)) {
                        numberXmas++;
                        System.out.println(" up r i: " + i + "j: " + j);

                    }
                    if (lookUpLeft(xmasMatrice, i, j)) {
                        numberXmas++;
                        System.out.println("up l i: " + i + "j: " + j);

                    }

                }
            }
        }
        System.out.println("countX: " + countX);

        System.out.println("numberXmas: " + numberXmas);

    }

    private static boolean lookLeft(Character[][] xmasMatrice, int colIndex, int rowIndex) {
        boolean result = true;
        if (colIndex > XMAS.length() - 2) {
            if (!checkLetter(xmasMatrice, rowIndex, colIndex - 1, M)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex, colIndex - 2, A)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex, colIndex - 3, S)) {
                return false;
            }
        } else {
            result = false;
        }
        return result;
    }

    private static boolean lookRight(Character[][] xmasMatrice, int colIndex, int rowIndex) {
        boolean result = true;
        int colLength = xmasMatrice[0].length;
        if (colIndex <= colLength - XMAS.length()) {
            if (!checkLetter(xmasMatrice, rowIndex, colIndex + 1, M)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex, colIndex + 2, A)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex, colIndex + 3, S)) {
                return false;
            }
        } else {
            result = false;
        }
        return result;
    }

    private static boolean lookDown(Character[][] xmasMatrice, int colIndex, int rowIndex) {
        boolean result = true;
        int rowLength = xmasMatrice.length;
        if (rowIndex <= rowLength - XMAS.length()) {
            if (!checkLetter(xmasMatrice, rowIndex + 1, colIndex, M)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex + 2, colIndex, A)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex + 3, colIndex, S)) {
                return false;
            }
        } else {
            result = false;
        }
        return result;
    }

    private static boolean lookUp(Character[][] xmasMatrice, int colIndex, int rowIndex) {
        boolean result = true;
        if (rowIndex > XMAS.length() - 2) {
            if (!checkLetter(xmasMatrice, rowIndex - 1, colIndex, M)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex - 2, colIndex, A)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex - 3, colIndex, S)) {
                return false;
            }
        } else {
            result = false;
        }
        return result;
    }

    private static boolean lookUpLeft(Character[][] xmasMatrice, int colIndex, int rowIndex) {
        boolean result = true;
        if (colIndex > XMAS.length() - 2 && rowIndex > XMAS.length() - 2) {
            if (!checkLetter(xmasMatrice, rowIndex - 1, colIndex - 1, M)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex - 2, colIndex - 2, A)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex - 3, colIndex - 3, S)) {
                return false;
            }
        } else {
            result = false;
        }
        return result;
    }

    private static boolean lookUpRight(Character[][] xmasMatrice, int colIndex, int rowIndex) {
        boolean result = true;
        int colLength = xmasMatrice[0].length;
        if (colIndex <= colLength - XMAS.length() && rowIndex > XMAS.length() - 2) {
            if (!checkLetter(xmasMatrice, rowIndex - 1, colIndex + 1, M)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex - 2, colIndex + 2, A)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex - 3, colIndex + 3, S)) {
                return false;
            }
        } else {
            result = false;
        }
        return result;
    }

    private static boolean lookDownLeft(Character[][] xmasMatrice, int colIndex, int rowIndex) {
        boolean result = true;
        int rowLength = xmasMatrice.length;
        if (colIndex > XMAS.length() - 2 && rowIndex <= rowLength - XMAS.length()) {
            if (!checkLetter(xmasMatrice, rowIndex + 1, colIndex - 1, M)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex + 2, colIndex - 2, A)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex + 3, colIndex - 3, S)) {
                return false;
            }
        } else {
            result = false;
        }
        return result;
    }

    private static boolean lookDownRight(Character[][] xmasMatrice, int colIndex, int rowIndex) {
        boolean result = true;
        int rowLength = xmasMatrice.length;
        int colLength = xmasMatrice[0].length;

        if (colIndex <= colLength - XMAS.length() && rowIndex <= rowLength - XMAS.length()) {
            if (!checkLetter(xmasMatrice, rowIndex + 1, colIndex + 1, M)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex + 2, colIndex + 2, A)) {
                return false;
            }
            if (!checkLetter(xmasMatrice, rowIndex + 3, colIndex + 3, S)) {
                return false;
            }
        } else {
            result = false;
        }
        return result;
    }

    private static boolean checkLetter(Character[][] xmasMatrice, int colIndex, int rowIndex, Character letter) {
        return letter.equals(xmasMatrice[rowIndex][colIndex]);
    }

    private static boolean checkAllLetter(Character[][] xmasMatrice, int colIndex, int rowIndex, Boolean rowPlus, Boolean colPlus) {
        for (int i = 1; i < XMAS.length(); i++) {
            int row = rowIndex;
            if (rowPlus != null && rowPlus) {
                row = rowIndex + i;
            } else if (rowPlus != null && !rowPlus) {
                row = rowIndex - i;
            }

            int col = colIndex;
            if (colPlus != null && colPlus) {
                col = colIndex + i;
            } else if (colPlus != null && !colPlus) {
                col = colIndex - i;
            }


            if (!checkLetter(xmasMatrice, row, col, XMAS.charAt(i - 1))) {
                return false;
            }
        }
        return true;
    }

}
