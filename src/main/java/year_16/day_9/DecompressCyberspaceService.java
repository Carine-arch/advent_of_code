package year_16.day_9;

import utils.PuzzleInputUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecompressCyberspaceService {

    public int part1(String filename) {
        String toDecompress = PuzzleInputUtils.getStringFromFile(filename);

        int index = 0;
        // je recupere le premier marker
        Matcher matcher = Pattern.compile("\\([0-9]+x[0-9]+\\)").matcher(toDecompress);
        while (matcher.hasMatch()) {
            String group = matcher.group();
            System.out.println(group);
        }

        return 0;
    }
}
