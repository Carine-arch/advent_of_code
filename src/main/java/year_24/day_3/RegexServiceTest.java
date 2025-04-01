package year_24.day_3;

import utils.PuzzleInputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexServiceTest {

    public void cleanComputerProgram() {
        String puzzleInput = PuzzleInputUtils.getStringFromFile("src/main/resources/puzzle_input/year_24_day_3_test");

        String input = puzzleInput.lines().collect(Collectors.joining());


        String inputEnabled = getInputWithoutDont(input);

        //String inputEnabled = getInputBetweenDoAndDont(puzzleInput);
        System.out.println("Segments entre do() et don't(): " + inputEnabled);


        String regex = "(mul)[(]\\d+[,]\\d+[)]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputEnabled);

        // Récupère chaque match au regex dans une liste
        List<String> correctInputList = new ArrayList<>();
        while (matcher.find()) {
            correctInputList.add(matcher.group());
        }

        // taille de la liste des résultats corrects
        System.out.println("words " + correctInputList.size());

        int resultPart1 = addMulInput(correctInputList);
        System.out.println("resultPart1 " + resultPart1);


        //result part 1 : 183380722
        // result part 2 : 82733683

    }

    // Méthode test avec tous les échecs ! Pour retirer tout ce qu'il y a entre don't et do
    private static String getInputWithoutDont(String puzzleInput) {
        // String regexDont = "don't[(][)].*do[(][)]|don't[(][)].*$";
        // String regexDont = "don't\\(\\).*?do\\(\\)|don't\\(\\).*$";
        String regexDont = """
                don't\\(\\)(?:(?!do\\(\\)).)+do\\(\\)|don't\\(\\).*$""";
//        String regexDont = "don't\\(\\)((?:(?!do\\(\\)).)*)do\\(\\)";
        System.out.println("regexDont " + regexDont);

        String result = puzzleInput.replaceAll(regexDont, "");

//        Pattern pattern = Pattern.compile(regexDont);
//        Matcher matcher = pattern.matcher(puzzleInput);
//
//        // Récupère chaque match au regex dans une liste
//        String result = "";
//        result = matcher.replaceAll("");
        System.out.println("inputEnabled " + result);
//
//        List<String> badInputs = new ArrayList<>();
//        while (matcher.find()) {
//            badInputs.add(matcher.group());
//            System.out.println("badInputs " + matcher.group());
//        }

//
//        for (String badInput : badInputs) {
//            System.out.println("badInput " + badInput);
//            puzzleInput = puzzleInput.replace(badInput, "hep");
//        }
//        System.out.println("puzzleInput " + puzzleInput);


//        String[] results = puzzleInput.split(regexDont);
//        for (String badInput : results) {
//            System.out.println("badInput " + badInput);
//        }
//        String result = Arrays.toString(Arrays.stream(results).filter(s -> !s.contains("don't()")).toArray());

        //  System.out.println("result " + result);


//        ~*when()mul(163,891)who(){[mul(521,763)what()";
//        145233 + 397523 = 542756 + 71661749 = 72204505

        return result;
    }


    // Méthode pour prendre ce qui correspond à ma regex entre do et dont
    private static String getInputBetweenDoAndDont(String puzzleInput) {
        //String regexDoDont = "do\\(\\)(.*?)(?=don't\\(\\))";
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


    private int addMulInput(List<String> correctInputList) {
        String regex = "[a-z]+[(]|[)]";
        List<Integer> resultToAdd = new ArrayList<>();
        for (String input : correctInputList) {
            // Enlève mul( et ) du string et fait un tableau avec les deux nombres
            String[] numberToMultiply = input.replaceAll(regex, "").split(",");
            // multiplie les 2 nombres du mul
            resultToAdd.add(Integer.parseInt(numberToMultiply[0]) * Integer.parseInt(numberToMultiply[1]));
        }
        return resultToAdd.stream().reduce(0, Integer::sum);
    }

}
