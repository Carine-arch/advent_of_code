package year_16.day_10;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ZoomingAroundBot {
    private int number;

    private List<Integer> microships = new ArrayList<>();

    private Rule rule;

    public Integer getLowestMicroship() {
        return microships.stream()
                .min(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("No microships available"));
    }

    public Integer getHighestMicroship() {
        return microships.stream()
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("No microships available"));
    }
}
