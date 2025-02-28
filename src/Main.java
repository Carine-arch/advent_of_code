import main.java.day_8.ISegmentService;
import main.java.day_8.SegmentService;
import main.java.day_8.TreeService;
import main.java.year_24.day_1.LocationService;
import main.java.year_24.day_2.ReportService;
import main.java.year_24.day_3.RegexService;
import main.java.year_24.day_4.XMASService;
import main.java.year_24.day_5.ProtocolService;
import main.java.year_24.day_7.Test;
import main.java.year_24.day_8.EasterEggService;
import main.java.year_24.day_8.PatrolService2;
import main.resources.puzzle_input.PuzzleInput_21_8;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {


    public static void main(String[] args) {

        System.out.println("Hello world!");
        year24Day8();
    }

    private static void year21Day8() {
        ISegmentService segmentService = (ISegmentService) new SegmentService();
//        int result = segmentService.count1478(new PuzzleInput().getPuzzleInputTest());
//        System.out.println("1,4,7 and 8 are " + result + " time in the output");
        //result part 1 : 534
        //segmentService.test(new HashSet<>(Arrays.asList("fdeba beagfd gbafe dagb dbf ecfad bd dgcaefb fbecgd abfecg | dgba dfb ecadf bdf")));

        segmentService.test(new PuzzleInput_21_8().getPuzzleInput());

        //result part 2 : 1070188
    }

    private static void year22Day8() {
        TreeService treeService = new TreeService();

        treeService.resolve();
    }

    private static void year24Day1() {
        LocationService locationService = new LocationService();
        locationService.countDistance();
    }

    private static void year24Day2() {
        ReportService reportService = new ReportService();
        reportService.countSafeReport();
    }

    private static void year24Day3() {
        RegexService regexService = new RegexService();
        regexService.cleanComputerProgram();
    }

    private static void year24Day4() {
        XMASService xmasService = new XMASService();
        xmasService.count();
    }

    private static void year24Day5() {
        ProtocolService protocolService = new ProtocolService();
        protocolService.day245();
    }

    private static void year24Day6() {
        PatrolService2 patrolService = new PatrolService2();
        try {
            patrolService.patrol2();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void year24Day7() {
//        CalibrationService calibrationService = new CalibrationService();
//        calibrationService.calibration();
        Test test = new Test();
        test.main(null);
    }

    private static void year24Day8() {
        EasterEggService easterEggService = new EasterEggService();
        try {
            easterEggService.part1();
            easterEggService.part2();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}