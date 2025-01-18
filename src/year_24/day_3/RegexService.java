package year_24.day_3;

import puzzle_input.PuzzleInput_24_3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexService {

    // https://javascript.info/regexp-greedy-and-lazy
    // https://fr.javascript.info/regexp-lookahead-lookbehind

    public void cleanComputerProgram() {
        String puzzleInput = new PuzzleInput_24_3().getPuzzleInput();

        String regexDont = "don't\\(\\)(?:(?!do\\(\\)).)+do\\(\\)|don't\\(\\).*$";

        String inputWithoutDont = puzzleInput.lines().collect(Collectors.joining()).replaceAll(regexDont, "");

        String regexMul = "mul[(](\\d+)[,](\\d+)[)]";

        Pattern pattern = Pattern.compile(regexMul);
        Matcher matcher = pattern.matcher(inputWithoutDont);

        // Récupère chaque match au regex dans une liste
        int result = 0;
        while (matcher.find()) {
            result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }

        System.out.println("result " + result);


        // result part 1 : 183380722
        // result part 2 : 82733683

    }


    private static String getInputBetweenDoAndDont(String puzzleInput) {
        String regexDoDont = "do\\(\\)(.*?)(?=don't\\(\\))|^.*?don't\\(\\)";

        String input = puzzleInput.lines().collect(Collectors.joining());

        Pattern pattern = Pattern.compile(regexDoDont);
        Matcher matcher = pattern.matcher(input);

        // Récupère chaque segment entre do() et don't()
        List<String> extractedSegments = new ArrayList<>();
        while (matcher.find()) {
            extractedSegments.add(matcher.group());
        }

        // Convertir la liste en une chaîne ou utiliser selon besoin
        return String.join("", extractedSegments);
    }


}
