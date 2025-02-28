package main.java.utils;

public enum DirectionEnum {

    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UPLEFT(-1, -1),
    UPRIGHT(-1, 1),
    DOWNLEFT(1, -1),
    DOWNRIGHT(1, 1);

    private final int rowDelta;
    private final int colDelta;

    DirectionEnum(int rowDelta, int colDelta) {
        this.rowDelta = rowDelta;
        this.colDelta = colDelta;
    }

    public int getRowDelta() {
        return rowDelta;
    }

    public int getColDelta() {
        return colDelta;
    }

    public static DirectionEnum fromDeltas(int rowDelta, int colDelta) {
        for (DirectionEnum direction : values()) {
            if (direction.rowDelta == rowDelta && direction.colDelta == colDelta) {
                return direction;
            }
        }
        return null; // ou throw une exception personnalisée si nécessaire
    }

    public static DirectionEnum getRightDirection(DirectionEnum previousDirection) {
        return switch (previousDirection) {
            case UP -> DirectionEnum.RIGHT;
            case RIGHT -> DirectionEnum.DOWN;
            case DOWN -> DirectionEnum.LEFT;
            case LEFT -> DirectionEnum.UP;
            default -> throw new IllegalArgumentException("Invalid direction: " + previousDirection);
        };
    }
}