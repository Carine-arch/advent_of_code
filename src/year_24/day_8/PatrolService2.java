package year_24.day_8;

import utils.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PatrolService2 {

    private static final Character OBSTACLE = '#';
    private static final Character GUARD = '^';
    private static final Character VISITED_POSITION = 'X';
    private static final Character NEW_OBSTACLE = 'O';
    private static final DirectionEnum INITIAL_DIRECTION = DirectionEnum.UP;


    public void patrol1() throws URISyntaxException, IOException {
        List<String> puzzleInput = Files.lines(Path.of(PatrolService2.class.getClassLoader().getResource("puzzle_input/puzzle_input_y24_d8").toURI())).collect(Collectors.toList());
        List<String> puzzleInputTest = Files.lines(Path.of(PatrolService2.class.getClassLoader().getResource("puzzle_input/puzzle_input_y24_d8_test").toURI())).collect(Collectors.toList());

        Matrice matrice = new PuzzleInputUtils().transformInputToMatrice(puzzleInput);

        Set<Position> visitedPositions = getAllOrientedVisitedPositions(matrice).stream().map(OrientedPosition::position).collect(Collectors.toSet());

        System.out.println("out of loop " + visitedPositions.size());
        printMatrice(matrice, visitedPositions);
    }

    private Set<OrientedPosition> getAllOrientedVisitedPositions(Matrice matrice) {
        Set<OrientedPosition> visitedPositions = new HashSet<>();
        Position initialGuardPosition = matrice.getPositionByValue(GUARD.toString()).stream().findFirst().orElseThrow();
        OrientedPosition nextPositions = new OrientedPosition(initialGuardPosition, INITIAL_DIRECTION);
        while (nextPositions != null) {
            visitedPositions.add(nextPositions);
            nextPositions = getNextOrientedPosition(matrice, nextPositions.position(), nextPositions.direction());
        }
        return visitedPositions;
    }

    private OrientedPosition getNextOrientedPosition(Matrice matrice, Position currentPosition, DirectionEnum direction,
                                                     Position obstacle) {
        OrientedPosition orientedPosition = new OrientedPosition(
                matrice.getNextPositionByDirection(direction, currentPosition), direction);
        if (orientedPosition.position() == null) {
            return null;
        } else if (orientedPosition.position().value().equals(OBSTACLE.toString()) ||
                orientedPosition.position().equals(obstacle)) {
            return getNextOrientedPosition(matrice, currentPosition, DirectionEnum.getRightDirection(direction));
        } else {
            return orientedPosition;
        }
    }

    private OrientedPosition getNextOrientedPosition(Matrice matrice, Position currentPosition, DirectionEnum direction) {
        return getNextOrientedPosition(matrice, currentPosition, direction, null);
    }

    public void patrol2() throws URISyntaxException, IOException {
        List<String> puzzleInput = Files.lines(Path.of(PatrolService2.class.getClassLoader().getResource("puzzle_input/puzzle_input_y24_d8").toURI())).collect(Collectors.toList());
        List<String> puzzleInputTest = Files.lines(Path.of(PatrolService2.class.getClassLoader().getResource("puzzle_input/puzzle_input_y24_d8_test").toURI())).collect(Collectors.toList());

        Matrice matrice = new PuzzleInputUtils().transformInputToMatrice(puzzleInput);

        Set<Position> visitedPositions = getAllOrientedVisitedPositions(matrice).stream().map(OrientedPosition::position).collect(Collectors.toSet());
        Set<Position> obstaclePositions = getAllPossibleObstaclePositions(matrice);

        System.out.println("number of infinite loop " + obstaclePositions.size());
        printMatrice2(matrice, visitedPositions, obstaclePositions);
    }

    private Set<Position> getAllPossibleObstaclePositions(Matrice matrice) {
        Set<Position> obstaclePositions = new HashSet<>();
        final Set<OrientedPosition> visitedOrientedPositions = getAllOrientedVisitedPositions(matrice);

        int count = 0;
        int total = visitedOrientedPositions.size();
        // visitedOrientedPositions.forEach(currentOrientedPosition -> {
        //     System.out.println("Position " + count + " / " + total);
        //     if (isPositionOkForObstacle(matrice, currentOrientedPosition)
        //             && isPatrolInfiniteWithObstacle(matrice, currentOrientedPosition.position())) {
        //         obstaclePositions.add(currentOrientedPosition.position());
        //    }
        //});

        for (OrientedPosition currentOrientedPosition : visitedOrientedPositions) {
            System.out.println("Position " + count + " / " + total);
            count++;


            if (isPositionOkForObstacle(matrice, currentOrientedPosition)
                    && isPatrolInfiniteWithObstacle(matrice, currentOrientedPosition.position())) {
                obstaclePositions.add(currentOrientedPosition.position());
            }
        }

        return obstaclePositions;
    }

    private boolean isPositionOkForObstacle(Matrice matrice, OrientedPosition currentPosition) {
        Position initialGuardPosition = matrice.getPositionByValue(GUARD.toString()).stream().findFirst().orElseThrow();
        return currentPosition != null && !currentPosition.position().value().equals(OBSTACLE.toString())
                && !currentPosition.position().equals(initialGuardPosition);
    }

    private boolean isPatrolInfiniteWithObstacle(Matrice matrice, Position obstacle) {
        Set<OrientedPosition> visitedOrientedPositions = new HashSet<>();
        Position initialGuardPosition = matrice.getPositionByValue(GUARD.toString()).stream().findFirst().orElseThrow();

        OrientedPosition nextOrientedPositions = new OrientedPosition(initialGuardPosition, INITIAL_DIRECTION);
        while (nextOrientedPositions != null) {
            visitedOrientedPositions.add(nextOrientedPositions);
            DirectionEnum currentDirection = nextOrientedPositions.direction();
            // get next position
            nextOrientedPositions = getNextOrientedPosition(matrice, nextOrientedPositions.position(), currentDirection,
                    obstacle);
            if (visitedOrientedPositions.contains(nextOrientedPositions)) {
                return true;
            }
        }
        return false;
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

    private void printMatrice2(Matrice matrice, Set<Position> visitedPositions, Set<Position> obstaclePositions) {
        Position initialGuardPosition = matrice.getPositionByValue(GUARD.toString()).stream().findFirst().orElseThrow();
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
