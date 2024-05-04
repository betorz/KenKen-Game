package main.domain.classes;

import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.HashSet;


public class Board {
    private static int nextId = 0;
    private int id;
    private int size;
    private int difficulty; //1->EASY, 2->MEDIUM, 3->HARD, 4->EXPERT
    private List<Region> regions;
    private Cell[][] cells;

    public Board(int size) {
        this.id = getNextId();
        this.size = size;
        this.regions = new ArrayList<>();
        this.cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j, 0);
            }
        }
    }

    public Board(int size, int id) {
        this.id = id;
        this.size = size;
        this.regions = new ArrayList<>();
        this.cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j, 0);
            }
        }
    }

    public Board copy() {
        Board bcopy = new Board(size, id);
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell ccopy = cells[i][j].copyCell();
                bcopy.setCell(i, j, ccopy);
            }
        }
        for (Region region : regions) {
            Region rcopy = region.copy();
            bcopy.addRegion(rcopy);
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell nextCell = cells[i][j].getNextCell();
                int rNext = nextCell.getRow();
                int cNext = nextCell.getColumn();
                Cell copyNextCell = bcopy.getCell(rNext, cNext);
                Cell copyCell = bcopy.getCell(i, j);
                copyCell.setNextCell(copyNextCell);
            }
        }
        bcopy.calculateDifficulty();
        return bcopy;
    }

    public void addRegion(Region r) {
        regions.add(r);
    }


    public void makeRegion(int op, int result, List<Pair<Integer, Integer>> coordCells) {
        int numCells = coordCells.size();
        Region newRegion = null;
        switch (op) {
            case 0:
                newRegion = new Equal(result, numCells);
                break;
            case 1:
                newRegion = new Addition(result, numCells);
                break;
            case 2:
                newRegion = new Subtraction(result, numCells);
                break;
            case 3:
                newRegion = new Multiplication(result, numCells);
                break;
            case 4:
                newRegion = new Division(result, numCells);
                break;
            case 5:
                newRegion = new Module(result, numCells);
                break;
            case 6:
                newRegion = new Power(result, numCells);
                break;
        }
        regions.add(newRegion);
        int regId = regions.size() - 1;

        for (int i = 0; i < numCells; ++i) {
            Pair<Integer, Integer> p = coordCells.get(i);
            int r = p.getX();
            int c = p.getY();
            Cell cell = cells[r][c];
            cell.setRegId(regId);

            Pair<Integer, Integer> pNext = coordCells.get((i + 1)%numCells);
            int rNext = pNext.getX();
            int cNext = pNext.getY();
            cell.setNextCell(cells[rNext][cNext]);
        }
      
    }

    private static int getNextId() {
        return nextId++;
    }

    public void decreaseNextId() {
        nextId--;
    }

    public int getId() {
        return id;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getSize() {
        return size;
    }

    public int getNumRegions() {
        return regions.size();
    }

    public char getRegionOp(int i) {
        return regions.get(i).getOperation();
    }

    public int getRegionResult(int i) {
        return regions.get(i).getResult();
    }

    public void calculateDifficulty() {
        difficulty = 1;
        HashSet<Character> operations = new HashSet<>();
        for (Region region : regions) {
            operations.add(region.getOperation());
            if(region.getResult() >= size*3) ++difficulty;
        }
        if (operations.size() >= 4) ++difficulty;
        difficulty = Math.min(difficulty, 4);

    }

    public int getCellValue(int r, int c) {
        if (r >= 0 && r < size && c >= 0 && c < size) {
            return cells[r][c].getValue();
        }
        return -1;
    }

    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    public void setCell(int r, int c, Cell cell) {
        cells[r][c] = cell;
    }

    public void modifyCellValue(int value, int r, int c) {
            cells[r][c].setValue(value);
    }

    public void clearCellValue(int r, int c) {
        cells[r][c].clear();
    }

    public boolean checkRowColRule(int r, int c, int v) {
    
        if (v != 0) {
            for (int i = 0; i < size; i++) {
                if (i != c && cells[r][i].getValue() == v)
                    return false;

                if (i != r && cells[i][c].getValue() == v)
                    return false;
            }
        }
        return true;
    }  

    public boolean checkOpRule(int r, int c) {
        Cell cell = cells[r][c];
        int regId = cell.getRegId();
        Region region = regions.get(regId);

        int numCells = region.getNumCells();
        List<Integer> values = new ArrayList<>(numCells);
        for (int i = 0; i < numCells; ++i) {
            int value = cell.getValue();
            if (value != 0) values.add(value);
            cell = cell.getNextCell();
        }
        return region.checkResult(values);
    }

    public int[][] getCellValuesMatrix() {
        int[][] values = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                values[i][j] = cells[i][j].getValue();
            }
        }
        return values;
    }

    public int[][] getCellRegIdsMatrix() {
        int[][] regIds = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                regIds[i][j] = cells[i][j].getRegId();
            }
        }
        return regIds;
    }
    
    public boolean checkSolution() {
        HashSet<Integer> regionsId = new HashSet<>();
        for (int i = 0; i < size; i++) {
            HashSet<Integer> rowValues = new HashSet<>();
            HashSet<Integer> colValues = new HashSet<>();
            for (int j = 0; j < size; j++) {
                int rowValue = cells[i][j].getValue();
                int colValue = cells[j][i].getValue();
                if (rowValue == 0 || colValue == 0 || !rowValues.add(rowValue) || !colValues.add(colValue)) {
                    return false;
                }
                
                if (regionsId.add(cells[i][j].getRegId())) {
                    if (!checkOpRule(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean solveBoard() {
        Solver solver = new Solver(this);
        return solver.solve();
    }

    public boolean getHint() {
        Board copyBoard = copy();
        copyBoard.solveBoard();
        Random rand = new Random();
        int size = copyBoard.getSize();
        int row = rand.nextInt(size);
        int col = rand.nextInt(size);
        boolean found = false;
        int solValue = copyBoard.getCellValue(row, col);
        int value = getCellValue(row, col);
        while (!found) { 
            if (value != solValue) found = true;
            else {
                ++col;
                if (col >= size) {
                    col = 0;
                    row = (row+1)%size;
                }
            }
            solValue = copyBoard.getCellValue(row, col);
            value = getCellValue(row, col);
        }
        modifyCellValue(solValue, row, col);
        if (checkSolution()) return true;
        return false;
    }

}