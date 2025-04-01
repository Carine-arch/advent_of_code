import org.junit.Assert;
import org.junit.Test;
import year_16.day_9.DecompressCyberspaceService;

public class DecompressCyberspaceServiceTest {
    DecompressCyberspaceService decompressCyberspaceService = new DecompressCyberspaceService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(57, decompressCyberspaceService.part1("src/main/resources/puzzle_input/year_16_day_9_test"));
        //System.out.println("Part 1: " + decompressCyberspaceService.part1("src/main/resources/puzzle_input/year_15_day_9"));
    }
}
