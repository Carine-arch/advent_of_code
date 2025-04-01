import org.junit.Assert;
import org.junit.Test;
import year_24.day_8.EasterEggService;

public class EasterEggServiceTest {

    EasterEggService easterEggService = new EasterEggService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(14, easterEggService.part1("src/main/resources/puzzle_input/puzzle_input_y24_d8_test"));
        System.out.println("Part 1: " + easterEggService.part1("src/main/resources/puzzle_input/puzzle_input_y24_d8"));
    }

    @Test
    public void partTwoTest() {
        Assert.assertEquals(34, easterEggService.part2("src/main/resources/puzzle_input/puzzle_input_y24_d8_test"));
        System.out.println("Part 2: " + easterEggService.part2("src/main/resources/puzzle_input/puzzle_input_y24_d8"));
    }

}
