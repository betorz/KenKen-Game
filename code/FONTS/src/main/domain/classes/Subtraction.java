package main.domain.classes;

import java.util.List;

public class Subtraction extends Region {
    public Subtraction(int result, int numCells) {
        super(result, '-', numCells);
    }

    public Region copy () {
        return new Subtraction(this.result, this.numCells);
    }

    public boolean checkResult(List<Integer> values) {
        if (values.size() < numCells) return true;
        int value1 = values.get(0);
        int value2 = values.get(1);
        return Math.abs(value1 - value2) == result;
    }
}