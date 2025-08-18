import org.junit.Assert;
import org.junit.Test;
import year_16.day_10.ZoomingAroundBotService;

public class ZoomingAroundBotServiceTest {
    ZoomingAroundBotService zoomingAroundBotService = new ZoomingAroundBotService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(2, zoomingAroundBotService.part1("src/main/resources/puzzle_input/year_16_day_10_test", 2, 5));
        System.out.println("Part 1: " + zoomingAroundBotService.part1("src/main/resources/puzzle_input/year_16_day_10", 17, 61));
    }

}
