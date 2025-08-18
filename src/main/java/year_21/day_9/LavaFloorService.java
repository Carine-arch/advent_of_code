package year_21.day_9;

import utils.DirectionEnum;
import utils.Matrice;
import utils.Position;
import utils.PuzzleInputUtils;

import java.util.ArrayList;
import java.util.List;

public class LavaFloorService {

    public int part1(String filename) {
        Matrice matrice = PuzzleInputUtils
                .transformInputToMatrice(PuzzleInputUtils.getLinesFromFile(filename));
        List<Integer> results = test(matrice);
        return results.stream().reduce(Integer::sum).orElse(0);
    }

    private List<Integer> test(Matrice matrice) {
        List<Integer> minValues = new ArrayList<>();
        matrice.positions().forEach(position -> {
                    int minValue = 9;
                    for (int i = 0; i < 4; i++) {
                        Position nextPosition = matrice.getNextPositionByDirection(DirectionEnum.values()[i], position);
                        if (nextPosition == null) {
                            continue;
                        }
                        int value = Integer.parseInt(nextPosition.value());
                        minValue = Math.min(minValue, value);
                    }
                    if (Integer.parseInt(position.value()) < minValue) {
                        minValues.add(Integer.parseInt(position.value()) + 1);
                    }
                }
        );
        return minValues;
    }

}
