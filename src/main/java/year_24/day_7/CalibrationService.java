package year_24.day_7;

import utils.PuzzleInputUtils;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class CalibrationService {

    public void calibration() {
        List<String> inputs = PuzzleInputUtils.getLinesFromFile("src/main/resources/puzzle_input/year_24_day_7_test");
        Set<BigInteger> results = new HashSet<>();

        // Transform input to map with result and value list of number to calculate
        inputs.forEach(input -> {
            String[] splitInput = input.split(":");

            BigInteger key = new BigInteger(splitInput[0].trim());
            List<BigInteger> values = Arrays.stream(splitInput[1].trim().split(" "))
                    .map(BigInteger::new).collect(Collectors.toList());
            System.out.println("key " + key + " values " + values.toString());

            //Reverse values to not take in count precedence
            Collections.reverse(values);
            List<BigInteger> expressions = generateExpressions(values);
            System.out.println(expressions);

            expressions.forEach(expression -> {
                if (Objects.equals(expression, key)) {
                    results.add(expression);
                }
            });
        });
        BigInteger sum = results.stream().reduce(BigInteger.ZERO, BigInteger::add);
        System.out.println("totalCalibration " + sum);
    }

//    public static List<String> generateExpressions(List<Integer> values) {
//        List<String> result = new ArrayList<>();
//
//        // Cas de base : si la liste a un seul élément, aucun opérateur à ajouter
//        if (values.size() == 1) {
//            result.add(String.valueOf(values.get(0)));
//            return result;
//        }
//
//        // Premier élément de la liste
//        int first = values.get(0);
//
//        // Récupérer le reste de la liste
//        List<Integer> remaining = values.subList(1, values.size());
//
//        // Récursivement générer toutes les combinaisons pour le reste
//        List<String> subExpressions = generateExpressions(remaining);
//
//        // Ajouter '+' et '*' entre le premier élément et chaque sous-expression
//        for (String subExp : subExpressions) {
//            result.add(first + "+" + subExp);
//            result.add(first + "*" + subExp);
//        }
//
//        return result;
//    }

    public static List<BigInteger> generateExpressions(List<BigInteger> values) {
        List<BigInteger> result = new ArrayList<>();

        // Cas de base : si la liste a un seul élément, aucun opérateur à ajouter
        if (values.size() == 1) {
            result.add(values.get(0));
            return result;
        }

        // Premier élément de la liste
        BigInteger first = values.get(0);

        // Récupérer le reste de la liste
        List<BigInteger> remaining = values.subList(1, values.size());

        // Récursivement générer toutes les combinaisons pour le reste
        List<BigInteger> subResults = generateExpressions(remaining);

        // Ajouter les résultats pour '+' et '*' entre le premier élément et chaque sous-résultat
        for (BigInteger subRes : subResults) {
            result.add(first.add(subRes)); // Addition
            result.add(first.multiply(subRes)); // Multiplication
        }

        return result;
    }

}
