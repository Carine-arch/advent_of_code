package year_24.day_4;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private static final List<Integer> UP = List.of(-1, 0);
    private static final List<Integer> DOWN = List.of(1, 0);
    private static final List<Integer> LEFT = List.of(0, -1);
    private static final List<Integer> RIGHT = List.of(0, 1);
    private static final List<Integer> UPLEFT = List.of(-1, -1);
    private static final List<Integer> UPRIGHT = List.of(-1, 1);
    private static final List<Integer> DOWNLEFT = List.of(1, -1);
    private static final List<Integer> DOWNRIGHT = List.of(1, 1);

    private static final List<List<Integer>> DIRECTIONS = List.of(UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT);

    private final int rowIndex;

    private final int colIndex;

    private final Character letter;

    private final Matrice matrice;

    public Position(int rowIndex, int colIndex, Character letter, Matrice matrice) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.letter = letter;
        this.matrice = matrice;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public Character getLetter() {
        return letter;
    }

    public int countXMAS(String xmas) {
        int numberXmas = 0;
        for (List<Integer> direction : DIRECTIONS) {
            boolean ok = true;
            Position position = this;
            for (int i = 1; i < xmas.length(); i++) {
                position = position.getNextPositionByDirection(direction);
                if (position == null || !position.getLetter().equals(xmas.charAt(i))) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                numberXmas++;
            }
        }

        return numberXmas;
    }

    public int countX_MAS() {
        int numberXmas = 0;

        if (this.letter.equals('A') && checkDiagonal(List.of(UPLEFT, DOWNRIGHT))
                && checkDiagonal(List.of(DOWNLEFT, UPRIGHT))) {
            numberXmas++;
        }

        return numberXmas;
    }

    public boolean checkDiagonal(List<List<Integer>> directions) {
        List<Character> MS = List.of('M', 'S');
        List<Character> lettersDiag = new ArrayList<>();
        for (List<Integer> direction : directions) {
            Position position = getNextPositionByDirection(direction);
            if (position != null) {
                lettersDiag.add(position.getLetter());
            } else {
                return false;
            }
        }
        return lettersDiag.containsAll(MS);
    }

    public Position getNextPositionByDirection(List<Integer> direction) {
        return matrice.getPosition(rowIndex + direction.get(0), colIndex + direction.get(1));
    }

}
