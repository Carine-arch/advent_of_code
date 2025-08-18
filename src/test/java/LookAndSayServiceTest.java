import org.junit.Assert;
import org.junit.Test;
import year_15.day_10.LookAndSayService;

public class LookAndSayServiceTest {
    LookAndSayService lookAndSayService = new LookAndSayService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(6, lookAndSayService.part1("1", 5));
        System.out.println("Part 1: " + lookAndSayService.part1("1321131112", 40));
    }

    @Test
    public void partTwoTest() {
        Assert.assertEquals(6, lookAndSayService.part1("1", 5));
        System.out.println("Part 2: " + lookAndSayService.part1("1321131112", 50));
    }
}
