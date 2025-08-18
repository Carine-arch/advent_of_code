import org.junit.Assert;
import org.junit.Test;
import year_22.day_9.RopeBridgeService;

public class RopeBridgeServiceTest {
    RopeBridgeService ropeBridgeService = new RopeBridgeService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(13, ropeBridgeService.part1("src/main/resources/puzzle_input/year_22_day_9_test"));
        System.out.println("Part 1: " + ropeBridgeService.part1("src/main/resources/puzzle_input/year_22_day_9")); //6367
    }
}
