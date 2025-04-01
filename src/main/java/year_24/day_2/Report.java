package year_24.day_2;

import java.util.List;

public class Report {

    private boolean isDescending = false;

    private List<Integer> inputs;

    public List<Integer> getInputs() {
        return inputs;
    }

    public void setInputs(List<Integer> inputs) {
        this.inputs = inputs;
    }

    public void setDescending() {
        int descending = 0;
        int ascending = 0;
        for (int i = 0; i < inputs.size() - 1; i++) {
            int gap = inputs.get(i + 1) - inputs.get(i);
            if (gap > 0) {
                ascending++;
            } else if (gap < 0) {
                descending++;
            }
        }
        isDescending = descending > ascending;
    }

    public boolean getDescending() {
        return isDescending;
    }

}
