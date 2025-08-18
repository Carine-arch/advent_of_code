import org.junit.Assert;
import org.junit.Test;
import year_24.day_9.AmphipodCompactingFileService;

public class AmphipodCompactingFileServiceTest {
    AmphipodCompactingFileService amphipodCompactingFileService = new AmphipodCompactingFileService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(1928, amphipodCompactingFileService.part1("src/main/resources/puzzle_input/year_24_day_9_test"));
        System.out.println("Part 1: " + amphipodCompactingFileService.part1("src/main/resources/puzzle_input/year_24_day_9"));
        //6353658451014 //73ms
    }
}
