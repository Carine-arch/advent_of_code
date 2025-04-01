package year_24.day_6;

import utils.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PatrolService {

    private static final Character OBSTACLE = '#';
    private static final Character GUARD = '^';
    private static final Character VISITED_POSITION = 'X';
    private static final Character NEW_OBSTACLE = 'O';
    private static final DirectionEnum INITIAL_DIRECTION = DirectionEnum.UP;


    public void patrol1() throws URISyntaxException, IOException {
        List<String> puzzleInput = Files.lines(Path.of(PatrolService.class.getClassLoader().getResource("puzzle_input/puzzle_input_y24_d8").toURI())).collect(Collectors.toList());
        List<String> puzzleInputTest = Files.lines(Path.of(PatrolService.class.getClassLoader().getResource("puzzle_input/puzzle_input_y24_d8_test").toURI())).collect(Collectors.toList());

        Matrice matrice = new PuzzleInputUtils().transformInputToMatrice(puzzleInput);

        Set<Position> visitedPositions = getAllVisitedPositions(matrice);

        System.out.println("out of loop " + visitedPositions.size());
        printMatrice(matrice, visitedPositions);
    }

    private Set<Position> getAllVisitedPositions(Matrice matrice) {
        Set<Position> visitedPositions = new HashSet<>();
        Position initialGuardPosition = getGuardPosition(matrice);
        OrientedPosition nextPositions = new OrientedPosition(initialGuardPosition, INITIAL_DIRECTION);
        while (nextPositions != null) {
            visitedPositions.add(nextPositions.position());
            nextPositions = getNextOrientedPosition(matrice, nextPositions.position(), nextPositions.direction());
        }
        return visitedPositions;
    }

    public void patrol2() throws URISyntaxException, IOException {
        List<String> puzzleInput = Files.lines(Path.of(PatrolService.class.getClassLoader().getResource("puzzle_input/puzzle_input_y24_d8").toURI())).collect(Collectors.toList());
        List<String> puzzleInputTest = Files.lines(Path.of(PatrolService.class.getClassLoader().getResource("puzzle_input/puzzle_input_y24_d8_test").toURI())).collect(Collectors.toList());

        Matrice matrice = new PuzzleInputUtils().transformInputToMatrice(puzzleInputTest);

//        Set<OrientedPosition> visitedOrientedPositions = getAllVisitedOrientedPositions(matrice);
//        Set<Position> obstaclePositions = getAllPossibleObstaclePositions(matrice, visitedOrientedPositions);

//        System.out.println("out of loop " + obstaclePositions.size());
//        printMatrice2(matrice, getAllVisitedPositions(matrice), obstaclePositions, getGuardPosition(matrice));
    }

    private Set<OrientedPosition> getAllVisitedOrientedPositions(Matrice matrice, Position obstacle) {
        Set<OrientedPosition> visitedOrientedPositions = new HashSet<>();
        Position initialGuardPosition = getGuardPosition(matrice);

        OrientedPosition nextOrientedPositions = new OrientedPosition(initialGuardPosition, INITIAL_DIRECTION);
        while (nextOrientedPositions != null) {
            visitedOrientedPositions.add(nextOrientedPositions);
            DirectionEnum direction = nextOrientedPositions.direction();
            if (nextOrientedPositions.position() == obstacle) {
                direction = getNewDirection(direction);
            }
            nextOrientedPositions = getNextOrientedPosition(matrice, nextOrientedPositions.position(), direction);
        }
        System.out.println("out of loop oriented " + visitedOrientedPositions.size());

        return visitedOrientedPositions;
    }

    private Set<Position> getAllPossibleObstaclePositions(Matrice matrice) {
        Set<Position> obstaclePositions = new HashSet<>();

        Set<Position> visitedPositions = getAllVisitedPositions(matrice);
        DirectionEnum direction = INITIAL_DIRECTION;
        Position initialGuardPosition = getGuardPosition(matrice);
        OrientedPosition nextOrientedPositions = new OrientedPosition(initialGuardPosition, direction);
        visitedPositions.stream().forEach(position -> {
            if (isNextPositionOkForObstacle(matrice, position, direction)) {
//                nextOrientedPositions = getNextOrientedPosition(matrice, nextOrientedPositions.position(), nextOrientedPositions.direction());
//                OrientedPosition obstacle = new OrientedPosition(initialGuardPosition, direction);
//                getAllVisitedOrientedPositions(matrice, obstacle);
            }
        });

        //System.out.println("out of loop oriented " + visitedOrientedPositions.size());

        return obstaclePositions;
    }

//    private Set<Position> getAllPossibleObstaclePositions(Matrice matrice, Set<OrientedPosition> visitedOrientedPositions) {
//
//        Set<Position> obstaclePositions = new HashSet<>();
//
//        visitedOrientedPositions.stream().forEach(orientedPosition -> {
//            if (isNextPositionOkForObstacle(matrice, orientedPosition.position(), orientedPosition.direction(), visitedOrientedPositions)) {
//                obstaclePositions.add(matrice.getNextPositionByDirection(orientedPosition.direction(), orientedPosition.position()));
//            }
//        });
//
//        System.out.println("out of loop oriented " + visitedOrientedPositions.size());
//
//        return obstaclePositions;
//    }

    private Position getGuardPosition(Matrice matrice) {
        return matrice.positions().stream().filter(position -> position.value().equals(GUARD.toString())).findFirst().orElseThrow();
    }

    private DirectionEnum getNewDirection(DirectionEnum previousDirection) {
        return switch (previousDirection) {
            case UP -> DirectionEnum.RIGHT;
            case RIGHT -> DirectionEnum.DOWN;
            case DOWN -> DirectionEnum.LEFT;
            case LEFT -> DirectionEnum.UP;
            default -> throw new IllegalArgumentException("Invalid direction: " + previousDirection);
        };
    }

    private OrientedPosition getNextOrientedPosition(Matrice matrice, Position currentPosition, DirectionEnum direction) {
        OrientedPosition orientedPosition = new OrientedPosition(matrice.getNextPositionByDirection(direction, currentPosition), direction);
        if (orientedPosition.position() == null) {
            return null;
        } else if (orientedPosition.position().value().equals(OBSTACLE.toString())) {
            return getNextOrientedPosition(matrice, currentPosition, getNewDirection(direction));
        } else {
            return orientedPosition;
        }
    }

    /**
     * Vérifie si la prochaine position pourrait être une position pour un obstacle
     * 1: récupérer la prochaine position
     * Si la prochaine position est un obstacle ou la position initiale alors on retourne false
     * Sinon on calcul la prochaine position en ajoutant un obstacle en face donc en devant changer de direction
     * Si cette prochaine position a déjà été visitée dans la même direction alors le garde va boucler : on renvoie true
     *
     * @param matrice
     * @param currentPosition
     * @param direction
     * @return
     */
//    private boolean isNextPositionOkForObstacle(Matrice matrice, Position currentPosition, DirectionEnum direction, Set<OrientedPosition> visitedOrientedPositions) {
//        Position initialGuardPosition = getGuardPosition(matrice);
//        OrientedPosition nextOrientedPosition = new OrientedPosition(matrice.getNextPositionByDirection(direction, currentPosition), direction);
//        if (nextOrientedPosition.position() != null && (nextOrientedPosition.position().value().equals(OBSTACLE.toString()) || nextOrientedPosition.position().equals(initialGuardPosition))) {
//            return false;
//        } else {
//            nextOrientedPosition = getNextOrientedPosition(matrice, currentPosition, getNewDirection(direction));
//            return visitedOrientedPositions.contains(nextOrientedPosition);
//        }
//    }
    private boolean isNextPositionOkForObstacle(Matrice matrice, Position currentPosition, DirectionEnum direction) {
        Position initialGuardPosition = getGuardPosition(matrice);
        Position nextPosition = matrice.getNextPositionByDirection(direction, currentPosition);
        return nextPosition != null && (nextPosition.value().equals(OBSTACLE.toString()) || nextPosition.equals(initialGuardPosition));
    }

    private void printMatrice(Matrice matrice, Set<Position> visitedPositions) {
        for (int i = 0; i < matrice.positions().size(); i++) {
            Position position = matrice.positions().get(i);
            if (i != 0 && position.rowIndex() != matrice.positions().get(i - 1).rowIndex()) {
                System.out.println("\n");
            }
            if (visitedPositions.contains(position)) {
                System.out.print(VISITED_POSITION);
            } else {
                System.out.print(position.value());
            }
        }
    }

    private void printMatrice2(Matrice matrice, Set<Position> visitedPositions, Set<Position> obstaclePositions, Position initialGuardPosition) {
        for (int i = 0; i < matrice.positions().size(); i++) {
            Position position = matrice.positions().get(i);
            String lineBreak = "";
            if (i != matrice.positions().size() - 1 && position.rowIndex() != matrice.positions().get(i + 1).rowIndex()) {
                lineBreak = "\n";
            }
            if (position.equals(initialGuardPosition)) {
                System.out.print(GUARD + lineBreak);
            } else if (obstaclePositions.contains(position)) {
                System.out.print(NEW_OBSTACLE + lineBreak);
            } else if (visitedPositions.contains(position)) {
                System.out.print(VISITED_POSITION + lineBreak);
            } else {
                System.out.print(position.value() + lineBreak);
            }
        }
    }

}
