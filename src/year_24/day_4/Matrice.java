package year_24.day_4;

import java.util.List;

public class Matrice {

    private List<Position> positions;

    private int rowLength;

    private int colLength;

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public int getColLength() {
        return colLength;
    }

    public void setColLength(int colLength) {
        this.colLength = colLength;
    }

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }

    public Position getPosition(int rowIndex, int colIndex) {
        return positions.stream().filter(pos -> pos.getRowIndex() == rowIndex && pos.getColIndex() == colIndex).findFirst().orElse(null);
    }
}
