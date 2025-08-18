import org.junit.Assert;
import org.junit.Test;
import year_18.day_9.MarbleCircleHellService;

public class MarbleCircleHellServiceTest {
    MarbleCircleHellService service = new MarbleCircleHellService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(8317, service.part1(10, 1618));
        //System.out.println("Part 1: " + decompressCyberspaceService.part1("src/main/resources/puzzle_input/year_15_day_9"));
    }
}
