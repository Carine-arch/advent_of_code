package year_24.day_5;

import puzzle_input.PuzzleInput_24_5;

import java.util.*;

public class ProtocolServiceTest {
    public void day245() {
        PuzzleInput_24_5 puzzleInput245 = new PuzzleInput_24_5();
        String rules = puzzleInput245.getPuzzleInputRules();
        String results = puzzleInput245.getPuzzleInputResult();

        countOrdered(rules, results);

        // part 1 : 6041

    }

    private void countOrdered(String rules, String results) {
        int numberOrdered = 0;
        List<Integer> middleOrderedPage = new ArrayList<>();
        List<Integer> middleNonOrderedPage = new ArrayList<>();

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
        List<String> sortedList = new ArrayList<>();

        // Prepare la liste non ordonnée de toutes les pages
        for (String rule : rules.lines().toList()) {
            String[] splitRules = rule.split("\\|");
            if (!sortedList.contains(splitRules[0])) {
                sortedList.add(splitRules[0]);
            }
            if (!sortedList.contains(splitRules[1])) {
                sortedList.add(splitRules[1]);
            }
        }

        System.out.println("sortedList size " + sortedList.size());

        for (int i = sortedList.size() - 1; i >= 0; i--) {
            boolean wasSorted = false;
            String sorted = sortedList.get(i);
            // List<String> rulesForToSort = rules.lines().filter(line -> line.contains(sorted)).toList();
            while (!wasSorted) {
                wasSorted = sort(rules.lines().toList(), sortedList);
            }
        }

        System.out.println("sortedList size " + sortedList.size());

        return sortedList;
    }


    private static boolean sort(List<String> rulesForToSort, List<String> sortedList) {
        boolean wasSorted = true;
        for (String rule : rulesForToSort) {
            String[] splitRules = rule.split("\\|");

            int index0 = sortedList.indexOf(splitRules[0]);
            int index1 = sortedList.indexOf(splitRules[1]);

            if (index0 != -1 && index1 != -1 && index0 > index1) {
                wasSorted = false;
                Collections.swap(sortedList, index0, index1);
//                sortedList.remove(index0);
//                sortedList.add(index1, splitRules[0]);
            }
        }
        return wasSorted;
    }


// sortedList.sort(() -> {
//        if (actualPage.equals(splitRules[1]) && nextPage.equals(splitRules[0])) {
//            return -1;
//        }
//        return 0;
//    });
}
