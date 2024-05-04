package test;

import main.domain.classes.Board;
import main.domain.classes.Region;
import main.domain.classes.Addition;
import main.domain.classes.Cell;
import main.domain.classes.Pair;
import main.persistence.controllers.CtrlBoardStorage;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    private Board board;
    private CtrlBoardStorage boards;

    @Before
    public void setUp() {
        board = new Board(3);
        boards = new CtrlBoardStorage();
        boards.addBoard(board);
    }
    
    @Test
    public void constructoraTest() {
        Board testBoard = boards.getBoard(0);
        assertEquals("The size of the board should be correctly set", 3, testBoard.getSize());
        assertEquals("The board should have no regions on creation", 0, testBoard.getNumRegions());
    }

    @Test
    public void addBoardTest() {
        Board secondBoard = new Board(4);
        boards.addBoard(secondBoard);
        assertEquals("The size of the boardsStorage should increase after adding a board", 2, boards.getNumBoards());
    }

    @Test
    public void removeBoardTest() {
        boards.removeLastBoard();
        assertEquals("The board list should be empty after removing the last board of the list", 0, boards.getNumBoards());
    }

    @Test
    public void getBoardTest() {
        Board secondBoard = new Board(4);
        boards.addBoard(secondBoard);
        assertSame("The getter of boards should return the correct board", secondBoard, boards.getBoard(1));
    }

    @Test
    public void addRegionTest() {
        Board testBoard = boards.getBoard(0);
        Region region = new Addition(3, 2);
        testBoard.addRegion(region);
        assertEquals("Board should have one region after adding", 1, testBoard.getNumRegions());
        int result = testBoard.getRegionResult(0);
        char op = testBoard.getRegionOp(0);
        int numCells = region.getNumCells();
        assertEquals("The result of the region is not correct", 3, result);
        assertEquals("The operation of the region is not correct", '+', op);
        assertEquals("The number of cells of the region is not correct", 2, numCells);
    }

    @Test
    public void boardCopyTest() {
        Board testBoard = boards.getBoard(0);
        List<Pair<Integer, Integer>> coordCells = new ArrayList<>();
        coordCells.add(new Pair<>(0, 0));
        coordCells.add(new Pair<>(1, 0));
        coordCells.add(new Pair<>(2, 0));
        testBoard.makeRegion(1, 6, coordCells);
        coordCells.add(new Pair<>(0, 1));
        coordCells.add(new Pair<>(1, 1));
        coordCells.add(new Pair<>(2, 1));
        testBoard.makeRegion(3, 6, coordCells);
        coordCells.add(new Pair<>(0, 2));
        coordCells.add(new Pair<>(1, 2));
        coordCells.add(new Pair<>(2, 2));
        testBoard.makeRegion(1, 6, coordCells);
        testBoard.modifyCellValue(1, 0, 0);
        testBoard.modifyCellValue(2, 0, 1);
        testBoard.modifyCellValue(3, 0, 2);
        testBoard.modifyCellValue(2, 1, 0);
        testBoard.modifyCellValue(3, 1, 1);
        testBoard.modifyCellValue(1, 1, 2);
        testBoard.modifyCellValue(3, 2, 0);
        testBoard.modifyCellValue(1, 2, 1);
        testBoard.modifyCellValue(1, 2, 2);
        testBoard.calculateDifficulty();

        Board copy = testBoard.copy();
        assertNotSame("The copy board should not be the same object as the original board", testBoard, copy);
        assertEquals("The copy should have the same size", testBoard.getSize(), copy.getSize());
        assertEquals("The copy should have the same id", testBoard.getId(), copy.getId());
        assertEquals("The copy should have the same difficulty", testBoard.getDifficulty(), copy.getDifficulty());
        assertEquals("The copy should have the same regions", testBoard.getNumRegions(), copy.getNumRegions());
        for (int i = 0; i < copy.getNumRegions(); ++i) {
            assertEquals("The copy should have the same regions", testBoard.getRegionOp(i), copy.getRegionOp(i));
            assertEquals("The copy should have the same regions", testBoard.getRegionResult(i), copy.getRegionResult(i));
        }
        int size = copy.getSize();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Cell c = testBoard.getCell(i, j);
                Cell copyc = copy.getCell(i, j);
                assertNotSame("The copy cells should not be the same object", c, copyc);
                assertEquals("The copy cells should have the same values", c.getValue(), copyc.getValue());
                assertEquals("The copy cells should have the same region id's", c.getRegId(), copyc.getRegId());
                Cell nextC = c.getNextCell();
                Cell nextCopyC = copyc.getNextCell();
                assertEquals("The copy cells should have the same next cell", nextC.getRow(), nextCopyC.getRow());
                assertEquals("The copy cells should have the same next cell", nextC.getColumn(), nextCopyC.getColumn());
            }
        }
    }

    @Test 
    public void calculateDifficultyTest() {
        Board testBoard = boards.getBoard(0);
        testBoard.calculateDifficulty();
        int dif = testBoard.getDifficulty();
        assertTrue("Board difficulty should be in the expected bounds", dif >= 1 && dif <=4);
    }

    @Test
    public void modifyCellValueTest() {
        Board testBoard = boards.getBoard(0);
        testBoard.modifyCellValue(2, 0, 0);
        assertEquals("The value of the cell should be modified", 2, testBoard.getCellValue(0, 0));
    }

    @Test
    public void checkRowColRuleTest() {
        Board testBoard = boards.getBoard(0);
        testBoard.modifyCellValue(1, 0, 0);
        testBoard.modifyCellValue(2, 0, 1);
        testBoard.modifyCellValue(3, 0, 2);
        testBoard.modifyCellValue(2, 1, 0);
        testBoard.modifyCellValue(3, 2, 0);
        assertTrue("The row column rule should be satisfied", testBoard.checkRowColRule(0, 0, 1));
        testBoard.modifyCellValue(1, 0, 2);
        assertFalse("The row column rule should  not be satisfied", testBoard.checkRowColRule(0, 0, 2));
    }

    @Test
    public void checkOpRuleTest() {
        Board testBoard = boards.getBoard(0);
        List<Pair<Integer, Integer>> coordCells = new ArrayList<>();
        coordCells.add(new Pair<>(0, 0));
        coordCells.add(new Pair<>(0, 1));
        coordCells.add(new Pair<>(0, 2));
        testBoard.makeRegion(1, 6, coordCells);
        testBoard.modifyCellValue(1, 0, 0);
        testBoard.modifyCellValue(2, 0, 1);
        testBoard.modifyCellValue(2, 0, 2);
        assertFalse("The operation rule should not be satisfied", testBoard.checkOpRule(0, 0));
        testBoard.modifyCellValue(3, 0, 2);
        assertTrue("The operation rule should be satisfied", testBoard.checkOpRule(0, 0));
    }

    @Test
    public void checkSolutionTest() {
        Board testBoard = boards.getBoard(0);
        List<Pair<Integer, Integer>> coordCells = new ArrayList<>();
        coordCells.add(new Pair<>(0, 0));
        coordCells.add(new Pair<>(1, 0));
        coordCells.add(new Pair<>(2, 0));
        testBoard.makeRegion(1, 6, coordCells);
        List<Pair<Integer, Integer>> coordCells2 = new ArrayList<>();
        coordCells2.add(new Pair<>(0, 1));
        coordCells2.add(new Pair<>(1, 1));
        coordCells2.add(new Pair<>(2, 1));
        testBoard.makeRegion(1, 6, coordCells2);
        List<Pair<Integer, Integer>> coordCells3 = new ArrayList<>();
        coordCells3.add(new Pair<>(0, 2));
        coordCells3.add(new Pair<>(1, 2));
        coordCells3.add(new Pair<>(2, 2));
        testBoard.makeRegion(1, 6, coordCells3);
        
        testBoard.modifyCellValue(1, 0, 0);
        testBoard.modifyCellValue(2, 0, 1);
        testBoard.modifyCellValue(3, 0, 2);
        testBoard.modifyCellValue(2, 1, 0);
        testBoard.modifyCellValue(3, 1, 1);
        testBoard.modifyCellValue(1, 1, 2);
        testBoard.modifyCellValue(3, 2, 0);
        testBoard.modifyCellValue(1, 2, 1);
        testBoard.modifyCellValue(1, 2, 2);

        assertFalse("The solution check should not be satisfied", testBoard.checkSolution());
         
        testBoard.modifyCellValue(2, 2, 2);
        assertTrue("The solution check should be satisfied", testBoard.checkSolution());
        
    }
}