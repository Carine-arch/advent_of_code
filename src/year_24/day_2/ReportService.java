package year_24.day_2;

import puzzle_input.PuzzleInput_24_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportService {

    private static final List<Integer> CORRECT_GAPS = Arrays.asList(1, 2, 3);


    public void countSafeReport() {
        String puzzleInput = new PuzzleInput_24_2().getPuzzleInput();
        List<String> lines = puzzleInput.lines().toList();

        int numberOfSafeReport = 0;
        for (String line : lines) {
            Report report = new Report();
            report.setInputs(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList());

            //report.setInputs(Arrays.asList(43, 45, 47, 57, 49));
            if (checkSafetyPart2(report)) {
                numberOfSafeReport++;
            }
        }

        System.out.println("numberOfSafeReport " + numberOfSafeReport);
        //result part1 631
        //result part 2 665


        //MickaREsult : 321, 386
    }

    // result [15, 19, 21, 22, 27, 29, 31] false

    private boolean checkSafetyPart1(Report report) {
        boolean result = true;
        report.setDescending();
        List<Integer> inputs = report.getInputs();
        for (int i = 0; i < inputs.size() - 1; i++) {

            if (isNotCorrectPart(report, i, i + 1)) {
                result = false;
                break;
            }
        }

        System.out.println("result " + inputs + " " + result);
        return result;
    }

    private boolean checkSafetyPart2(Report report) {
        boolean result = true;
        report.setDescending();
        List<Integer> inputs = report.getInputs();

        // result [45, 44, 47, 51, 53] false
        List<Integer> indexNotCorrect = new ArrayList<>();
        List<Integer> indexCorrectIfRemoved = new ArrayList<>();

        boolean isAlreadyNotCorrect = false;
        for (int i = 0; i < inputs.size() - 1; i++) {
            if (!indexCorrectIfRemoved.contains(i) && isNotCorrectPart(report, i, i + 1)) {
                if (isAlreadyNotCorrect) {
                    System.out.println("result " + report.getInputs() + " " + false);
                    return false;
                }
                isAlreadyNotCorrect = true;
                indexNotCorrect.add(i);

                int notCorrect = 0;
                // test en enlevant l'index actuel
                if (i != 0 && isNotCorrectPart(report, i - 1, i + 1)) {
                    notCorrect++;
                } else {
                    indexCorrectIfRemoved.add(i);
                }
                //test en enlevant l'index suivant
                if ((i != (inputs.size() - 2) && isNotCorrectPart(report, i, i + 2))) {
                    notCorrect++;
                } else {
                    indexCorrectIfRemoved.add(i + 1);
                }

                if (notCorrect == 2) {
                    result = false;
                    break;
                }
            }
        }

        System.out.println("result " + report.getInputs() + " " + result);
        return result;

        // 661 false
    }

    private boolean isNotCorrectPart(Report report, int indexMin, int indexMax) {
        boolean result = false;
        List<Integer> inputs = report.getInputs();
        int gap = inputs.get(indexMax) - inputs.get(indexMin);
        boolean isDescending = gap < 0;

        if (!CORRECT_GAPS.contains(Math.abs(gap)) || (isDescending != report.getDescending())) {
            result = true;
        }
        return result;
    }

}
