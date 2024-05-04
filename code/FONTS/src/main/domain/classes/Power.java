package main.domain.classes;

import java.util.List;

public class Power extends Region {
    public Power(int result, int numCells) {
        super(result, '^', numCells);
    }

    public Region copy () {
        return new Power(this.result, this.numCells);
    }

    public boolean checkResult(List<Integer> values) {
        if (values.size() < numCells) return true;
        int value1 = values.get(0);
        int value2 = values.get(1);
        return (Math.pow(value1, value2) == result) || (Math.pow(value2, value1) == result);
    }
}