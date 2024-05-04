package main.domain.classes;

import java.util.List;

public abstract class Region {
    protected int result;
    protected char operation;
    protected int numCells;

    public Region(int result, char operation, int numCells) {
        this.result = result;
        this.operation = operation;
        this.numCells = numCells;
    }

    public Character getOperation() {
        return operation;
    }

    public Integer getResult() {
        return result;
    }

    public Integer getNumCells() {
        return numCells;
    }

    public abstract Region copy();

    public abstract boolean checkResult(List<Integer> values);

}