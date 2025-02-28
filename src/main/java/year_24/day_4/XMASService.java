package main.java.year_24.day_4;

import main.resources.puzzle_input.PuzzleInput_24_4;

import java.util.ArrayList;
import java.util.List;

public class XMASService {

    private static final String XMAS = "XMAS";
    private static final Character X = 'X';
    private static final Character A = 'A';

    public void count() {
        Matrice xmasMatrice = transformInputToMatrice();
        countXMAS(xmasMatrice);
        // result part 1 2557
        countX_MAS(xmasMatrice);
        // result part 2 1854
    }

    private Matrice transformInputToMatrice() {
        List<String> input = new PuzzleInput_24_4().getPuzzleInput().lines().toList();
        Matrice matrice = new Matrice();

        List<Position> positions = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < input.size(); lineIndex++) {// line
            matrice.setRowLength(input.size());
            String line = input.get(lineIndex);
            for (int colIndex = 0; colIndex < line.length(); colIndex++) {//column
                matrice.setColLength(line.length());
                positions.add(new Position(lineIndex, colIndex, line.toCharArray()[colIndex], matrice));
            }
        }
        matrice.setPositions(positions);
        return matrice;
    }


    private void countXMAS(Matrice xmasMatrice) {
        int numberXmas = 0;

        int countX = 0;

        for (Position position : xmasMatrice.getPositions()) {
            if (position.getLetter().equals(X)) {
                countX++;
                numberXmas = numberXmas + position.countXMAS(XMAS);
            }
        }

        System.out.println("countX: " + countX);

        System.out.println("numberXmas: " + numberXmas);

    }

    private void countX_MAS(Matrice xmasMatrice) {
        int numberXmas = 0;

        int countA = 0;

        for (Position position : xmasMatrice.getPositions()) {
            if (position.getLetter().equals(A)) {
                countA++;
                numberXmas = numberXmas + position.countX_MAS();
            }
        }

        System.out.println("countA: " + countA);

        System.out.println("numberX_mas: " + numberXmas);

    }

}
