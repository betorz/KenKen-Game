package main.domain.classes;

public class Cell {
    private int row;
    private int column;
    private int value;
    private int regId;
    private Cell nextCell;

    public Cell(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public Cell copyCell() {
        Cell copy = new Cell(row, column, value);
        copy.setNextCell(getNextCell());
        copy.setRegId(getRegId());
        return copy;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getValue() {
        return value;
    }

    public int getRegId() {
        return regId;
    }

    public Cell getNextCell() {
        return nextCell;
    }

    public void setValue(int value) {
       this.value = value;
    }

    public void setNextCell(Cell nextCell) {
        this.nextCell = nextCell;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }


    public void clear() {
        this.value = 0;
    }
}