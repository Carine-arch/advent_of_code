package year_17.day_9;

import utils.PuzzleInputUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GarbageStreamService {

    public int part1(String filename) {
        String input = PuzzleInputUtils.getStringFromFile(filename);

        // je supprime tous les caractères inutiles après !
        String regexExlamation = "!.";
        String inputCleanOfExclamation = input.replaceAll(regexExlamation, "");
        System.out.println("inputclean" + inputCleanOfExclamation);

        // je supprime tous les garbages
        String regexGarbage = "<[^>]*>";
        String inputCleanOfGarbage = inputCleanOfExclamation.replaceAll(regexGarbage, "");
        System.out.println("inputclean2" + inputCleanOfGarbage);


        return calculateDepth(inputCleanOfGarbage);
    }

    public int part2(String filename) {
        String input = PuzzleInputUtils.getStringFromFile(filename);

        // je supprime tous les caractères inutiles après !
        String regexExclamation = "!.";
        // je compte le nombre de group correspondant à la regex
        String inputCleanOfExclamation = input.replaceAll(regexExclamation, "");
        System.out.println("inputclean" + inputCleanOfExclamation);

        // je supprime tous les garbages
        String regexGarbage = "<[^>]*>";
        String inputCleanOfGarbage = inputCleanOfExclamation.replaceAll(regexGarbage, "");

        // je compte le nombre de group correspondant à la regex
        Pattern pattern = Pattern.compile(regexGarbage);
        Matcher matcher = pattern.matcher(inputCleanOfExclamation);
        int regexGarbageCount = matcher.results().toList().size();
        System.out.println("regexGarbageCount" + regexGarbageCount);

        int inputLength = inputCleanOfExclamation.length() - (regexGarbageCount * 2);
        int result = inputLength - inputCleanOfGarbage.length();

        System.out.println("inputLength" + inputLength);
        System.out.println("result " + result);

        return result;
    }

    private int calculateDepth(String input) {
        // A chaque fois que je rencontre un '{' j'incrémente le depth
        // A chaque fois que je rencontre un '}' j'ajoute le depth au result et je décrémente le depth
        int depth = 0;
        int result = 0;

        for (char c : input.toCharArray()) {
            if (c == '{') {
                depth++;
            } else if (c == '}') {
                result += depth;
                depth--;
            }
        }

        System.out.println("result" + result);
        return result;
    }
}
