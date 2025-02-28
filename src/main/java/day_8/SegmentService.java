package main.java.day_8;

import java.util.*;
import java.util.stream.Collectors;

public class SegmentService implements ISegmentService {

    private static final String SEPARATOR_PIPE = " \\| ";
    private final Map<String, List<String>> mapDigit = new HashMap<>();
    private final Map<String, String> mapSegment = new HashMap<>();
    private final List<Integer> allDigit = new ArrayList<>();


    @Override
    public int count1478(Set<String> puzzleInput) {
        int count = 0;
        for (String line : puzzleInput) {
            String output = line.split(SEPARATOR_PIPE)[1];
            for (String digit : output.split(" ")) {
                if (digit.length() == 2 || digit.length() == 3 || digit.length() == 4 || digit.length() == 7) {
                    count++;
                }
            }
            System.out.println(output + "   " + count);
        }

        return count;
    }

    @Override
    public void test(Set<String> puzzleInput) {
        for (String line : puzzleInput) {
            String input = line.split(SEPARATOR_PIPE)[0];
            List<String> digits = Arrays.asList(input.split(" "));

            // separe segment
            List<String> digitOne = Arrays.asList(digits.stream().filter(digit -> digit.length() == 2).findFirst().orElse("").split(""));
            mapDigit("1", digitOne);

            List<String> digitSeven = Arrays.asList(digits.stream().filter(digit -> digit.length() == 3).findFirst().orElse("").split(""));
            mapDigit("7", digitSeven);

            List<String> digitFour = Arrays.asList(digits.stream().filter(digit -> digit.length() == 4).findFirst().orElse("").split(""));
            mapDigit("4", digitFour);

            List<String> digitEight = Arrays.asList(digits.stream().filter(digit -> digit.length() == 7).findFirst().orElse("").split(""));
            mapDigit("8", digitEight);


            resolveASegment(digitOne, digitSeven);

            //digitsWithFiveSegments
            List<String> digitsWithFiveSegments = digits.stream().filter(digit -> digit.length() == 5).toList();
            List<String> digitsWithSixSegments = digits.stream().filter(digit -> digit.length() == 6).toList();

            List<String> digitSix = resolveDigitSix(digitsWithSixSegments, digitOne);
            mapDigit("6", digitSix);

            resolveCSegment(digitSix, digitOne);
            resolveFSegment(digitOne);

            List<String> digitTwo = resolveDigitTwo(digitsWithFiveSegments);
            mapDigit("2", digitTwo);

            List<String> digitFive = resolveDigitFive(digitsWithFiveSegments);
            mapDigit("5", digitFive);

            List<String> digitThree = resolveDigitThree(digitsWithFiveSegments);
            mapDigit("3", digitThree);


            resolveBSegment(digitFour, digitThree);
            resolveDSegment(digitFour);
            resolveESegment(digitFive, digitSix);

            List<String> digitZero = resolveDigitZero(digitsWithSixSegments);
            mapDigit("0", digitZero);

            List<String> digitNine = resolveDigitNine(digitsWithSixSegments);
            mapDigit("9", digitNine);


            resolveGSegment(digitThree);

            System.out.println("map digit " + mapDigit);
            System.out.println("map segment " + mapSegment);

            String output = line.split(SEPARATOR_PIPE)[1];
            List<String> outputDigits = Arrays.asList(output.split(" "));

            resolveOutput(outputDigits);

        }
        addUpAll();
    }

    private void mapDigit(String key, List<String> value) {
        mapDigit.put(key, value);
        System.out.println(key + " is " + value);
    }

    private List<String> resolveDigitSix(List<String> digitsWithSixDigits, List<String> digitOne) {
        List<String> digitSix = new ArrayList<>();
        for (String digits : digitsWithSixDigits) {
            for (String digit : digitOne) {
                if (!digits.contains(digit)) {
                    digitSix = Arrays.asList(digits.split(""));
                }
            }
        }
        return digitSix;
    }

    private List<String> resolveDigitTwo(List<String> digitsWithFiveDigits) {
        List<String> digitTwo = new ArrayList<>();
        for (String digits : digitsWithFiveDigits) {
            if (!digits.contains(mapSegment.get("f"))) {
                digitTwo = Arrays.asList(digits.split(""));
            }
        }
        return digitTwo;
    }

    private List<String> resolveDigitFive(List<String> digitsWithFiveDigits) {
        List<String> digitFive = new ArrayList<>();
        for (String digits : digitsWithFiveDigits) {
            if (!digits.contains(mapSegment.get("c"))) {
                digitFive = Arrays.asList(digits.split(""));
            }
        }
        return digitFive;
    }

    private List<String> resolveDigitThree(List<String> digitsWithFiveDigits) {
        List<String> digitThree = new ArrayList<>();
        for (String digits : digitsWithFiveDigits) {
            if (digits.contains(mapSegment.get("c")) && digits.contains(mapSegment.get("f"))) {
                digitThree = Arrays.asList(digits.split(""));
            }
        }
        return digitThree;
    }

    private List<String> resolveDigitZero(List<String> digitsWithSixDigits) {
        List<String> digitZero = new ArrayList<>();
        for (String digits : digitsWithSixDigits) {
            if (!digits.contains(mapSegment.get("d"))) {
                digitZero = Arrays.asList(digits.split(""));
            }
        }
        return digitZero;
    }

    private List<String> resolveDigitNine(List<String> digitsWithSixDigits) {
        List<String> digitNine = new ArrayList<>();
        for (String digits : digitsWithSixDigits) {
            System.out.println(digits);
            if (!digits.contains(mapSegment.get("e"))) {
                digitNine = Arrays.asList(digits.split(""));
            }
        }
        return digitNine;
    }

    private void resolveASegment(List<String> digitOne, List<String> digitSeven) {
        String aSegment = digitSeven.stream().filter(digit -> !digitOne.contains(digit)).findFirst().orElseThrow();
        mapSegment.put("a", aSegment);
        System.out.println("the a segment is " + aSegment);
    }


    private void resolveCSegment(List<String> digitSix, List<String> digitOne) {
        String cSegment = digitOne.stream().filter(d -> !digitSix.contains(d)).findFirst().orElseThrow();
        mapSegment.put("c", cSegment);
        System.out.println("the c segment is " + cSegment);
    }

    private void resolveFSegment(List<String> digitOne) {
        String fSegment = digitOne.stream().filter(d -> !mapSegment.get("c").equals(d)).findFirst().orElseThrow();
        mapSegment.put("f", fSegment);
        System.out.println("the f segment is " + fSegment);
    }

    private void resolveBSegment(List<String> digitFour, List<String> digitThree) {
        String bSegment = digitFour.stream().filter(d -> !digitThree.contains(d)).findFirst().orElseThrow();
        mapSegment.put("b", bSegment);
        System.out.println("the b segment is " + bSegment);
    }

    private void resolveDSegment(List<String> digitFour) {
        String dSegment = digitFour.stream().filter(d -> !d.equals(mapSegment.get("b")) && !d.equals(mapSegment.get("c")) && !d.equals(mapSegment.get("f"))).findFirst().orElseThrow();
        mapSegment.put("d", dSegment);
        System.out.println("the d segment is " + dSegment);
    }

    private void resolveESegment(List<String> digitFive, List<String> digitSix) {
        String eSegment = digitSix.stream().filter(digit -> !digitFive.contains(digit)).findFirst().orElseThrow();
        mapSegment.put("e", eSegment);
        System.out.println("the e segment is " + eSegment);
    }

    private void resolveGSegment(List<String> digitThree) {
        String gSegment = digitThree.stream()
                .filter(d -> !d.equals(mapSegment.get("a")) && !d.equals(mapSegment.get("c")) && !d.equals(mapSegment.get("d")) && !d.equals(mapSegment.get("f")))
                .findFirst().orElseThrow();
        mapSegment.put("g", gSegment);
        System.out.println("the g Segment is " + gSegment);
    }

    private void resolveOutput(List<String> outputDigits) {
        List<String> digitList = new ArrayList<>();
        outputDigits.forEach(digit -> {
            List<String> splitDigit = Arrays.asList(digit.split(""));
            // TODO: Entry
            for (String key : mapDigit.keySet()) {
                if (splitDigit.size() == mapDigit.get(key).size() && splitDigit.containsAll(mapDigit.get(key))) {
                    digitList.add(key);
                }
            }
        });
        Integer output = Integer.parseInt(digitList.stream().map(String::valueOf).collect(Collectors.joining("")));
        allDigit.add(output);
        System.out.println("Result ? " + output);
    }

    private void addUpAll() {
        Integer finalResult = allDigit.stream().reduce(0, Integer::sum);
        System.out.println("final result is " + finalResult);
    }

}
