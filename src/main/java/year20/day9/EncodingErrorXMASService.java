package year20.day9;

import utils.PuzzleInputUtils;

import java.util.List;

public class EncodingErrorXMASService {

    public long part1(String filename, int preambleSize) {

        List<String> input = PuzzleInputUtils.getLinesFromFile(filename);
        List<Long> numbers = input.stream().map(Long::parseLong).toList();

        long result = 0L;
        for (int i = 0; i < numbers.size(); i++) {
            // liste des nombres à tester
            List<Long> possible = numbers.subList(i, i + preambleSize);

            // on vérifie la première valeur
            long valueToTest = numbers.get(i + preambleSize);
            if (!testValue(possible, valueToTest)) {
                result = valueToTest;
                break;
            }
        }

        return result;
    }

    private boolean testValue(List<Long> possible, long valueToTest) {
        for (int j = 0; j < possible.size() - 1; j++) {
            // on vérifie que la soustraction avec possiblevalue et une autre donne zéro
            long sub = valueToTest - possible.get(j);
            for (int i = j + 1; i < possible.size(); i++) {
                if (sub - possible.get(i) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
