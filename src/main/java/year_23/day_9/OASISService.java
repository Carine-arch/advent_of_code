package year_23.day_9;

import utils.PuzzleInputUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class OASISService {

    public int part1(String filename) {
        //Récupération des lignes du fichier
        List<String> lines = PuzzleInputUtils.getLinesFromFile(filename);

        return getSum(lines, true);
    }

    public int part2(String filename) {
        //Récupération des lignes du fichier
        List<String> lines = PuzzleInputUtils.getLinesFromFile(filename);

        return getSum(lines, false);
    }

    private Integer getSum(List<String> lines, boolean next) {
        //Pour chaque ligne on split sur les espaces pour récupérer chaque nombre
        // Convertit en tableau de int
        // On calcule la prédiction pour chaque ligne
        // On réduit la liste en sommant les résultats de chaque ligne
        return lines.stream()
                .map(line -> Arrays.asList(line.split(" ")))
                .map(strings -> strings.stream()
                        .map(Integer::parseInt)
                        .toList())
                .map(next ? this::calculateNextPrediction : this::calculatePreviousPrediction)
                .reduce(Integer::sum).orElseThrow();
    }

    private int calculateNextPrediction(List<Integer> numbers) {
        int result = numbers.get(numbers.size() - 1);
        // On créé une seconde liste pour stocker la différence entre chaque nombre
        List<Integer> diff = IntStream.range(0, numbers.size() - 1).map(i -> numbers.get(i + 1) - numbers.get(i)).boxed().toList();
        // si ne contient que des zéros on arrête
        if (diff.stream().allMatch(i -> i == 0)) {
            return result + diff.get(diff.size() - 1); // On retourne 0 si toutes les différences sont nulles
        }
        // sinon on créé une nouvelle liste avec les différences de la liste précédente => récursivité
        return calculateNextPrediction(diff) + result;
    }

    private int calculatePreviousPrediction(List<Integer> numbers) {
        int result = numbers.get(0);
        // On créé une seconde liste pour stocker la différence entre chaque nombre
        List<Integer> diff = IntStream.range(0, numbers.size() - 1).map(i -> numbers.get(i + 1) - numbers.get(i)).boxed().toList();
        // si ne contient que des zéros on arrête
        if (diff.stream().allMatch(i -> i == 0)) {
            return result - diff.get(0); // On retourne 0 si toutes les différences sont nulles
        }
        // sinon on créé une nouvelle liste avec les différences de la liste précédente => récursivité
        return result - calculatePreviousPrediction(diff);
    }
}
