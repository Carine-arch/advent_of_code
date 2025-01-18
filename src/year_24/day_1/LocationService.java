package year_24.day_1;

import puzzle_input.PuzzleInput_24_1;

import java.util.ArrayList;
import java.util.List;

public class LocationService {

    public void countDistance() {

        String puzzleInput = new PuzzleInput_24_1().getPuzzleInput();

        List<String> lines = puzzleInput.lines().toList();
        List<String> column1 = new ArrayList<>();
        List<String> column2 = new ArrayList<>();

        lines.forEach(line -> {
            String[] splitLine = line.split(" {3}");
            column1.add(splitLine[0]);
            column2.add(splitLine[1]);
        });

        column1.sort(String::compareTo);
        column2.sort(String::compareTo);

        // part1(column1, column2);
        //result part 1 : 1580061
        part2(column1, column2);
        //result part 2 : 23046913


    }

    private void part1(List<String> column1, List<String> column2) {
        List<Integer> diffList = new ArrayList<>();
        for (int i = 0; i < column1.size(); i++) {
            diffList.add(Math.abs(Integer.parseInt(column2.get(i)) - Integer.parseInt(column1.get(i))));
        }

        diffList.stream().reduce(Integer::sum).ifPresent(System.out::println);
    }

    private void part2(List<String> column1, List<String> column2) {
        List<Integer> diffList = new ArrayList<>();
        for (int i = 0; i < column1.size(); i++) {
            Integer number = Integer.parseInt(column1.get(i));
            Integer numberToMul = column2.stream().filter(col -> col.equals(number.toString())).toList().size();
            diffList.add(Math.abs(number * numberToMul));
        }

        diffList.stream().reduce(Integer::sum).ifPresent(System.out::println);
    }

}
