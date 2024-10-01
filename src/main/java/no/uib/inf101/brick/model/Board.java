package no.uib.inf101.brick.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class Board extends Grid<Character> {
    public static int score;
    int rows;
    int cols;
    String boardString;
    int removedRowCount;

    public Board(int rows, int cols) {
        super(rows, cols);
        this.rows = rows;
        this.cols = cols;
        initializeboard();
    }

    public void initializeboard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                set(new CellPosition(row, col), '-');
            }
        }

    }

}