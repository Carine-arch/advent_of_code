package year_22.day_9;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.Position;

@Data
@AllArgsConstructor
public class Rope {
    private Position headPosition;
    private Position tailPosition;

    public void moveCurrentHeadPosition(String direction) {
        headPosition = switch (direction) {
            case "U" -> new Position(headPosition.rowIndex() - 1, headPosition.colIndex(), "#");
            case "D" -> new Position(headPosition.rowIndex() + 1, headPosition.colIndex(), "#");
            case "L" -> new Position(headPosition.rowIndex(), headPosition.colIndex() - 1, "#");
            case "R" -> new Position(headPosition.rowIndex(), headPosition.colIndex() + 1, "#");
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }

    public void moveCurrentTailPosition() {
        int rowDelta = headPosition.rowIndex() - tailPosition.rowIndex();
        int colDelta = headPosition.colIndex() - tailPosition.colIndex();
        if (Math.abs(rowDelta) > 1 || Math.abs(colDelta) > 1) {
            // le tail doit se déplacer vers la position du head
            int newRow = tailPosition.rowIndex() + Integer.signum(rowDelta);
            int newCol = tailPosition.colIndex() + Integer.signum(colDelta);
            tailPosition = new Position(newRow, newCol, "#");
        }
    }
}
