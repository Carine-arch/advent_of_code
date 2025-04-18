import org.junit.Assert;
import org.junit.Test;
import year_17.day_9.GarbageStreamService;

public class GarbageStreamServiceTest {
    GarbageStreamService garbageStreamService = new GarbageStreamService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(16, garbageStreamService.part1("src/main/resources/puzzle_input/year_17_day_9_test"));
        System.out.println("Part 1: " + garbageStreamService.part1("src/main/resources/puzzle_input/year_17_day_9"));
    }

    @Test
    public void partTwoTest() {
        Assert.assertEquals(17, garbageStreamService.part2("src/main/resources/puzzle_input/year_17_day_9_test"));
        System.out.println("Part 2: " + garbageStreamService.part2("src/main/resources/puzzle_input/year_17_day_9"));
        // 9075 too high
    }
}
