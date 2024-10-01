package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {
    int cols;
    int rows;
    Object[][] grid;

    @SuppressWarnings("unchecked")
    // Constructore, this.grid value came from chatGPT, so not sure why it wanted me
    // to have a SuppresWarning.
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = (E[][]) new Object[rows][cols];
    }

    // Sets the value of every gridcell to defualtValue.
    public Grid(int rows, int cols, E defaultValue) {
        this(rows, cols); // Call the basic constructor
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = defaultValue;
            }
        }
    }

    // Sets a value for a spesific gridcell
    @Override
    public void set(CellPosition pos, E value) {
        if (!(pos.row() < rows && pos.col() < cols)) {
            throw new IndexOutOfBoundsException("Cant set a position not on the grid");
        }
        grid[pos.row()][pos.col()] = value;
    }

    // Got some help from chatGPT so dont know why it wanted a SuppressWarnings
    // Gets the current value at the specified position
    @SuppressWarnings("unchecked")
    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position is not on the grid.");
        }
        return (E) grid[pos.row()][pos.col()];
    }

    // Checks if the given position is on the the grid. Retuns true if.
    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        if (pos.row() >= this.rows || pos.row() < 0 || pos.col() >= this.cols || pos.col() < 0) {
            return false;
        } else {
            return true;
        }

    }

    // returns the number of rows
    @Override
    public int rows() {
        return this.rows;
    }

    // returns the number of cols
    @Override
    public int cols() {
        return this.cols;
    }

    public Object getGrid() {
        return this.grid;
    }

    // Creates an arraylist, iterates of rows and cols, for each gridcell creates an
    // new Gridcell with position and value
    @Override
    public Iterator<GridCell<E>> iterator() {
        List<GridCell<E>> list = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                CellPosition pos = new CellPosition(row, col);
                E value = get(pos);
                list.add(new GridCell<>(pos, value));
            }
        }
        return list.iterator();
    }

}
