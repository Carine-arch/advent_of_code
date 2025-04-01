import org.junit.Assert;
import org.junit.Test;
import year_15.day_9.SantaFacteurService;

public class SantaFacteurServiceTest {

    SantaFacteurService santaFacteurService = new SantaFacteurService();

    @Test
    public void partOneTest() {
        Assert.assertEquals(605, santaFacteurService.part1("src/main/resources/puzzle_input/year_15_day_9_test"));
        System.out.println("Part 1: " + santaFacteurService.part1("src/main/resources/puzzle_input/year_15_day_9"));
        Assert.assertEquals(605, santaFacteurService.calculateClusteredMinDistance("src/main/resources/puzzle_input/year_15_day_9_test"));
        System.out.println("Part 1: " + santaFacteurService.calculateClusteredMinDistance("src/main/resources/puzzle_input/year_15_day_9"));
    }

    @Test
    public void partTwoTest() {
        Assert.assertEquals(982, santaFacteurService.part2("src/main/resources/puzzle_input/year_15_day_9_test"));
        System.out.println("Part 2: " + santaFacteurService.part2("src/main/resources/puzzle_input/year_15_day_9"));
    }

}
