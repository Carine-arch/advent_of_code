package year_24.day_5;

import puzzle_input.PuzzleInput_24_5;

import java.util.*;

public class ProtocolService {

    public void day245() {
        PuzzleInput_24_5 puzzleInput245 = new PuzzleInput_24_5();
        String rules = puzzleInput245.getPuzzleInputRules();
        String results = puzzleInput245.getPuzzleInputResult();

        countOrdered(rules, results);

        // part 1 : 6041

    }

    private void countOrdered(String rules, String results) {
        int numberOrdered = 0;
        // Init liste des resultats ordonnés et non ordonnés
        List<Integer> middleOrderedPage = new ArrayList<>();
        List<Integer> middleNonOrderedPage = new ArrayList<>();

        // Liste des résultats
        List<String> resultLines = results.lines().toList();

        // Liste ordonnée de toutes les pages
        List<String> orderedRules = sortRules(rules);
        System.out.println("sortRules " + orderedRules);

        for (String result : resultLines) {
            List<String> splitResult = Arrays.asList(result.split(","));
            List<String> orderedResult = orderedPage(orderedRules, splitResult);

            if (orderedResult.equals(splitResult)) {
                middleOrderedPage.add(Integer.parseInt(orderedResult.get(orderedResult.size() / 2)));
                numberOrdered++;
            } else {
                middleNonOrderedPage.add(Integer.parseInt(orderedResult.get(orderedResult.size() / 2)));
            }
        }

        System.out.println("numberOrdered " + numberOrdered);
        System.out.println("middleOrderedPage " + middleOrderedPage);

        System.out.println("result part 1 : " + middleOrderedPage.stream().reduce(Integer::sum).orElseThrow());
        System.out.println("result part 2 : " + middleNonOrderedPage.stream().reduce(Integer::sum).orElseThrow());

    }

    // Compare ordre liste des results et liste ordonnée des pages
    private List<String> orderedPage(List<String> orderedRules, List<String> result) {
        List<String> orderedList = new ArrayList<>(result);
        System.out.println("nonorderedLsit " + result);

        orderedList.sort(Comparator.comparing(orderedRules::indexOf));

        System.out.println("orderedLsit " + orderedList);

        return orderedList;
    }

    private List<String> sortRules(String rules) {

        // Prepare une liste avec toutes les pages
        List<String> sortedList = buildRulesList(rules);
        // Boucle sur la liste des rules
        List<String> rulesList = rules.lines().toList();


        boolean wasSorted = false;
        while (!wasSorted) {
            int n = 0;

            List<String> tempSortedList = new ArrayList<>(sortedList);

            // Boucle sur les rules pour trier
            for (String rule : rulesList) {
                String[] splitRules = rule.split("\\|");

                int indexLeftRule = sortedList.indexOf(splitRules[0]);
                int indexRightRule = sortedList.indexOf(splitRules[1]);

                if (indexLeftRule > indexRightRule) {
                    Collections.swap(sortedList, indexLeftRule, indexRightRule);
                }

            }
            

            if (tempSortedList.equals(sortedList)) {
                n++;
                wasSorted = true;
            }

            System.out.println("n " + n);
        }

        return sortedList;
    }

    private List<String> buildRulesList(String rules) {
        List<String> pagesList = new ArrayList<>();
        // Prepare la liste non ordonnée de toutes les pages
        for (String rule : rules.lines().toList()) {
            String[] splitRules = rule.split("\\|");
            if (!pagesList.contains(splitRules[0])) {
                pagesList.add(splitRules[0]);
            }
            if (!pagesList.contains(splitRules[1])) {
                pagesList.add(splitRules[1]);
            }
        }
        return pagesList;
    }

}
