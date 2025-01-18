package utils;

import java.util.List;

public record Matrice(List<Position> positions, int rowLength, int colLength) {

    private static final List<Integer> UP = List.of(-1, 0);
    private static final List<Integer> DOWN = List.of(1, 0);
    private static final List<Integer> LEFT = List.of(0, -1);
    private static final List<Integer> RIGHT = List.of(0, 1);
    private static final List<Integer> UPLEFT = List.of(-1, -1);
    private static final List<Integer> UPRIGHT = List.of(-1, 1);
    private static final List<Integer> DOWNLEFT = List.of(1, -1);
    private static final List<Integer> DOWNRIGHT = List.of(1, 1);

    private static final List<List<Integer>> DIRECTIONS = List.of(UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT);


    public Position getPosition(int rowIndex, int colIndex) {
        return positions.stream().filter(pos -> pos.rowIndex() == rowIndex && pos.colIndex() == colIndex).findFirst().orElse(null);
    }

    public Position getNextPositionByDirection(List<Integer> direction, int rowIndex, int colIndex) {
        return getPosition(rowIndex + direction.get(0), colIndex + direction.get(1));
    }
}
