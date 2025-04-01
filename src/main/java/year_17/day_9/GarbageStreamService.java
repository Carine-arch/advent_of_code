package year_17.day_9;

import utils.PuzzleInputUtils;

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


        return 0;
    }

//    private void test() {
//        for (char c : inputCleanOfGarbage.toCharArray()) {
//        if (c == '{') {
//            depth++;
//            inGroup = true;
//        } else if (c == '}') {
//            if (inGroup) {
//                groupCount++;
//                inGroup = false;
//            }
//            depth--;
//        }
//    }
    }
}
