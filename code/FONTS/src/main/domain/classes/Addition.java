package main.domain.classes;

import java.util.List;

public class Addition extends Region {
    public Addition(int result, int numCells) {
        super(result, '+', numCells);
    }

    public Region copy () {
        return new Addition(this.result, this.numCells);
    }

    public boolean checkResult(List<Integer> values) {
        int addition = 0;
        for (int value : values) {   
            addition += value;
            if (addition > result) return false;
        }
        if (values.size() < numCells) return true;
        return result == addition;
    }
}