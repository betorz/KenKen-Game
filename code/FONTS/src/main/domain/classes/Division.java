package main.domain.classes;

import java.util.List;

public class Division extends Region {
    public Division(int result, int numCells) {
        super(result, '/', numCells);
    }

    public Region copy () {
        return new Division(this.result, this.numCells);
    }

    public boolean checkResult(List<Integer> values) {
        if (values.size() < numCells) return true;
        int value1 = values.get(0);
        int value2 = values.get(1);
        if (value1 > value2) return (value1%value2 == 0) && (value1/value2 == result);
        else return (value2%value1 == 0) && (value2/value1 == result);
    }
}