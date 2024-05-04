package test;

import main.domain.classes.Cell;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CellTest {
    private Cell cell;

    @Before
    public void setUp() {
        cell = new Cell(1, 2, 3);
    }

    @Test
    public void contructorTest() {
        assertEquals("Row should be initialized correctly", 1, cell.getRow());
        assertEquals("Column should be initialized correctly", 2, cell.getColumn());
        assertEquals("Value should be initialized correctly", 3, cell.getValue());
    }

    @Test 
    public void copyCellTest() {
        Cell copy = cell.copyCell();
        assertNotSame("The copy cell should not be the same object as the original cell", cell, copy);
        assertEquals("The row of the copy should be equal to the original", copy.getRow(), cell.getRow());
        assertEquals("The column of the copy should be equal to the original", copy.getColumn(), cell.getColumn());
        assertEquals("The value of the copy should be equal to the original", copy.getValue(), cell.getValue());
    }

    @Test
    public void setAndGetTest() {
        cell.setValue(2);
        assertEquals("Value should be update correctly", 2, cell.getValue());

        cell.setRegId(8);
        assertEquals("RegId should be update correctly", 8, cell.getRegId());

        Cell next = new Cell(1, 1, 2);
        cell.setNextCell(next);
        assertSame("The nextCell should be the same as the nextCell added", next, cell.getNextCell());
    }

    @Test
    public void clearTest() {
        cell.clear();
        assertEquals("Value should be 0 after be cleared", 0, cell.getValue());
    }
    
}
