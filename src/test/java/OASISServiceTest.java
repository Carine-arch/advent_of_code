import org.junit.Assert;
import org.junit.Test;
import year_23.day_9.OASISService;

public class OASISServiceTest {
    OASISService oasisService = new OASISService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(114, oasisService.part1("src/main/resources/puzzle_input/year_23_day_9_test"));
        System.out.println("Part 1: " + oasisService.part1("src/main/resources/puzzle_input/year_23_day_9"));
        // 809119189 too low // 1762065988
    }

    @Test
    public void partTwoTest() {
        Assert.assertEquals(2, oasisService.part2("src/main/resources/puzzle_input/year_23_day_9_test"));
        System.out.println("Part 2: " + oasisService.part2("src/main/resources/puzzle_input/year_23_day_9"));
        // 1066
    }
}
