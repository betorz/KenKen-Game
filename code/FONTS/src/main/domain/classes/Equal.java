package main.domain.classes;

import java.util.List;

public class Equal extends Region {
    public Equal(int result, int numCells) {
        super(result, '=', numCells);
    }

    public Region copy () {
        return new Equal(this.result, this.numCells);
    }

    public boolean checkResult(List<Integer> values) {
        return (values.get(0) == result);
    }
}