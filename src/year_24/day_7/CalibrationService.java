package year_24.day_7;

import puzzle_input.PuzzleInput_24_7;

import javax.script.ScriptException;
import java.util.*;

public class CalibrationService {

    //https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/

    public void calibration() {
        String puzzleInput = new PuzzleInput_24_7().getPuzzleInputTest();

        List<String> inputs = puzzleInput.lines().toList();
        Map<Integer, Integer[]> mapInput = new HashMap<>();

        List<Integer> goodCalibration = new ArrayList<>();
        inputs.forEach(input -> {
            String[] splitInput = input.split(":");

            Integer key = Integer.parseInt(splitInput[0].trim());
            Integer[] values = Arrays.stream(splitInput[1].trim().split(" "))
                    .map(Integer::parseInt).toArray(Integer[]::new);
            mapInput.put(key, values);

//            if (Objects.equals(key, Arrays.stream(values).reduce(0, Integer::sum))) {
//                goodCalibration.add(key);
//            } else if (Objects.equals(key, Arrays.stream(values).reduce(1, (a, b) -> a * b))) {
//                goodCalibration.add(key);
//            }
            try {
                testCalibration(key, values);
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }

        });


        // inputs.stream().reduce(0,Integer::sum);

    }

    private boolean testCalibration(Integer key, Integer[] values) throws ScriptException {
        boolean result = false;

//        ScriptEngineManager mgr = new ScriptEngineManager();
//        ScriptEngine engine = mgr.getEngineByName("JavaScript");
//        String foo = "40+2";
//        System.out.println(engine.eval(foo));

//        List<Character> operators = List.of('+', '*');
//
//        int numberOfPossibilities = operators.size() + operators.size() * (values.length - 1);
//
//        for (int i = 0; i < numberOfPossibilities; i++) {
//
//            // Arrays.stream(values).reduce(0, (a,b) -> );
//        }

        char[] operators = {'*', '+'};
        int r = values.length - 1;
        int n = operators.length;
        printCombination(operators, n, r);

        return result;
    }

    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Starting and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    static void combinationUtil(char[] arr, char[] data, int start,
                                int end, int index, int r) {
        // Current combination is ready to be printed, print it
        if (index == r) {
            for (int j = 0; j < r; j++) {
                System.out.print(data[j] + " ");
            }
            System.out.println("");
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i + 1, end, index + 1, r);
        }
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(char[] arr, int n, int r) {
        // A temporary array to store all combination one by one
        char[] data = new char[r];

        // Print all combination using temporary array 'data[]'
        combinationUtil(arr, data, 0, n - 1, 0, r);
    }


}
