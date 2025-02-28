package main.java.year_24.day_7;

import main.resources.puzzle_input.PuzzleInput_24_7;

import java.math.BigInteger;
import java.util.*;

public class CalibrationService {

    public void calibration() {
        String puzzleInput = new PuzzleInput_24_7().getPuzzleInputTest();

        List<String> inputs = puzzleInput.lines().toList();
        Map<BigInteger, List<Integer>> mapInput = new HashMap<>();

        // Transform input to map with result and value list of number to calculate
        inputs.forEach(input -> {
            String[] splitInput = input.split(":");

            BigInteger key = new BigInteger(splitInput[0].trim());
            List<Integer> values = Arrays.stream(splitInput[1].trim().split(" "))
                    .map(Integer::parseInt).toList();
            mapInput.put(key, values);
            System.out.println("key " + key + " values " + values.toString());

            List<String> expressions = generateExpressions(values);
            System.out.println(expressions);


        });


        // inputs.stream().reduce(0,Integer::sum);

    }

    public static void main(String[] args) {
        List<Integer> values = List.of(1, 2, 3);
        List<String> expressions = generateExpressions(values);
        System.out.println(expressions);
        List<Integer> values2 = List.of(1, 2, 3, 4, 5);
        List<String> expressions2 = generateExpressions(values2);
        System.out.println(expressions2);
        List<Integer> values3 = List.of(1, 2);
        List<String> expressions3 = generateExpressions(values3);
        System.out.println(expressions3);
    }

    public static List<String> generateExpressions(List<Integer> values) {
        List<String> result = new ArrayList<>();

        // Cas de base : si la liste a un seul élément, aucun opérateur à ajouter
        if (values.size() == 1) {
            result.add(String.valueOf(values.get(0)));
            return result;
        }

        // Premier élément de la liste
        int first = values.get(0);

        // Récupérer le reste de la liste
        List<Integer> remaining = values.subList(1, values.size());

        // Récursivement générer toutes les combinaisons pour le reste
        List<String> subExpressions = generateExpressions(remaining);

        // Ajouter '+' et '*' entre le premier élément et chaque sous-expression
        for (String subExp : subExpressions) {
            result.add(first + "+" + subExp);
            result.add(first + "*" + subExp);
        }

        return result;
    }

}
