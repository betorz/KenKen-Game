package test;

import main.domain.classes.Board;
import main.domain.classes.Solver;
import main.domain.classes.Pair;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SolverTest {
    private Board board;
    private Solver solver;

    @Before
    public void setUp() {
        board = new Board(3);
        solver = new Solver(board);
    }

    @Test
    public void testSolveableBoard() {
        List<Pair<Integer, Integer>> coordCells = new ArrayList<>();
        coordCells.add(new Pair<>(0, 0));
        coordCells.add(new Pair<>(1, 0));
        coordCells.add(new Pair<>(2, 0));
        board.makeRegion(1, 6, coordCells);
        List<Pair<Integer, Integer>> coordCells2 = new ArrayList<>();
        coordCells2.add(new Pair<>(0, 1));
        coordCells2.add(new Pair<>(1, 1));
        board.makeRegion(2, 2, coordCells2);
        List<Pair<Integer, Integer>> coordCells3 = new ArrayList<>();
        coordCells3.add(new Pair<>(0, 2));
        coordCells3.add(new Pair<>(1, 2));
        coordCells3.add(new Pair<>(2, 2));
        board.makeRegion(1, 6, coordCells3);
        List<Pair<Integer, Integer>> coordCells4 = new ArrayList<>();
        coordCells4.add(new Pair<>(2, 1));
        board.makeRegion(0, 2, coordCells4);

        boolean result = solver.solve();
        assertTrue("The solver should have found a solution", result);
    }

    @Test
    public void testUnsolveableBoard() {
        List<Pair<Integer, Integer>> coordCells = new ArrayList<>();
        coordCells.add(new Pair<>(0, 0));
        coordCells.add(new Pair<>(1, 0));
        coordCells.add(new Pair<>(2, 0));
        board.makeRegion(1, 6, coordCells);
        List<Pair<Integer, Integer>> coordCells2 = new ArrayList<>();
        coordCells2.add(new Pair<>(0, 1));
        coordCells2.add(new Pair<>(1, 1));
        board.makeRegion(2, 1, coordCells2);
        List<Pair<Integer, Integer>> coordCells3 = new ArrayList<>();
        coordCells3.add(new Pair<>(0, 2));
        coordCells3.add(new Pair<>(1, 2));
        coordCells3.add(new Pair<>(2, 2));
        board.makeRegion(1, 6, coordCells3);
        List<Pair<Integer, Integer>> coordCells4 = new ArrayList<>();
        coordCells4.add(new Pair<>(2, 1));
        board.makeRegion(0, 2, coordCells4);

        boolean result = solver.solve();
        assertFalse("The solver should not have found a solution", result);
    }
}
