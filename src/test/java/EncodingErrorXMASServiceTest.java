import org.junit.Assert;
import org.junit.Test;
import year20.day9.EncodingErrorXMASService;

public class EncodingErrorXMASServiceTest {
    EncodingErrorXMASService encodingService = new EncodingErrorXMASService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(127L, encodingService.part1("src/main/resources/puzzle_input/year_20_day_9_test", 5));
        System.out.println("Part 1: " + encodingService.part1("src/main/resources/puzzle_input/year_20_day_9", 25));
    }
}
