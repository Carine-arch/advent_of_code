import org.junit.Assert;
import org.junit.Test;
import year_21.day_9.LavaFloorService;

public class LavaFloorServiceTest {
    LavaFloorService lavaFloorService = new LavaFloorService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(15, lavaFloorService.part1("src/main/resources/puzzle_input/year_21_day_9_test"));
        System.out.println("Part 1: " + lavaFloorService.part1("src/main/resources/puzzle_input/year_21_day_9"));
    }
}
