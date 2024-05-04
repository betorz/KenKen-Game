package main.domain.classes;

public class Solver {
    private Board board;

    public Solver(Board board) {
        this.board = board;
    }

    public boolean solve() {
        return solve(0, 0); 

    }

    private boolean solve(int row, int col) {
        if (row == board.getSize()) {
            return board.checkSolution();
        }
        int nextRow = (col == board.getSize() - 1) ? row + 1 : row;
        int nextCol = (col == board.getSize() - 1) ? 0 : col + 1;

        if (board.getCellValue(row, col) != 0) {
            if (solve(nextRow, nextCol)) return true;
        }
            
        
        for (int i = 1; i <= board.getSize(); i++) {
            if (validValue(i, row, col)) {
                if (solve(nextRow, nextCol)) {
                    return true;
                }

                board.modifyCellValue(0, row, col);
            }
        }
        return false;
    }

    private boolean validValue(int value, int row, int col) {
        
        board.modifyCellValue(value, row, col); 
        boolean valid = board.checkRowColRule(row, col, value) && 
                          board.checkOpRule(row, col); 
        if (!valid)
            board.modifyCellValue(0, row, col); 
        return valid;
    }
}