package main.domain.classes;

import java.util.List;

public class Multiplication extends Region {
    public Multiplication(int result, int numCells) {
        super(result, '*', numCells);
    }

    public Region copy () {
        return new Multiplication(this.result, this.numCells);
    }

    public boolean checkResult(List<Integer> values) {
        int multiplication = 1;
        for (int value : values) {
            
            multiplication *= value;
            if (multiplication > result) return false;
        }
        if (values.size() < numCells) return true;
        return result == multiplication;
    }
}