package utils;

import java.util.List;

public record Matrice(List<Position> positions, int rowLength, int colLength) {

    public Position getPosition(int rowIndex, int colIndex) {
        return positions.stream()
                .filter(pos -> pos.rowIndex() == rowIndex && pos.colIndex() == colIndex)
                .findFirst()
                .orElse(null);
    }

    public List<Position> getPositionByValue(String value) {
        return positions.stream()
                .filter(pos -> pos.value().equals(value))
                .toList();
    }

    public Position getNextPositionByDirection(DirectionEnum direction, Position position) {
        return getPosition(position.rowIndex() + direction.getRowDelta(), position.colIndex() + direction.getColDelta());
    }

}
