package year_22.day_9;

import utils.Position;
import utils.PuzzleInputUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class RopeBridgeService {

    public int part1(String filename) {
        List<String> lines = PuzzleInputUtils.getLinesFromFile(filename);

        Set<Position> positionsList = new HashSet<>();
        // Initial position of the rope
        Position currentHeadPosition = new Position(0, 0, "#");
        Position currentTailPosition = new Position(0, 0, "#");
        Rope rope = new Rope(currentHeadPosition, currentTailPosition);
        //pour chaque ligne on créé un mouvement
        lines.stream()
                .map(line -> new Movement(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])))
                // on crée un stream de direction pour chaque mouvement
                .flatMap(movement ->
                        IntStream.range(0, movement.steps())
                                .mapToObj(i -> movement.direction())
                )
                // pour chaque direction on déplace le head et le tail
                .forEach(direction -> {
                    rope.moveCurrentHeadPosition(direction);
                    // on calcul le déplacement du tail
                    rope.moveCurrentTailPosition();
                    positionsList.add(rope.getTailPosition());
                });

        return positionsList.size();
    }

    public int part2(String filename) {
        List<String> lines = PuzzleInputUtils.getLinesFromFile(filename);

        Set<Position> positionsList = new HashSet<>();
        // Initial position of the rope
        Position currentHeadPosition = new Position(0, 0, "#");
        Position currentTailPosition = new Position(0, 0, "#");
        Rope rope = new Rope(currentHeadPosition, currentTailPosition);
        //pour chaque ligne on créé un mouvement
        lines.stream()
                .map(line -> new Movement(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])))
                // on crée un stream de direction pour chaque mouvement
                .flatMap(movement ->
                        IntStream.range(0, movement.steps())
                                .mapToObj(i -> movement.direction())
                )
                // pour chaque direction on déplace le head et le tail
                .forEach(direction -> {
                    rope.moveCurrentHeadPosition(direction);
                    // on calcul le déplacement du tail
                    rope.moveCurrentTailPosition();
                    positionsList.add(rope.getTailPosition());
                });

        return positionsList.size();
    }

    private Position moveKnot(Position headPosition, Position tailPosition) {
        Rope newRope = new Rope(headPosition, tailPosition);
        newRope.moveCurrentTailPosition();
        if (newRope.getTailPosition().equals(tailPosition)) {
            return newRope.getTailPosition(); // No movement needed
        }
        return moveKnot(tailPosition, newRope.getTailPosition());
    }

}
