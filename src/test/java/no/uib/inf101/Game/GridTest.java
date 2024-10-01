package no.uib.inf101.Game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.brick.model.Board;
import no.uib.inf101.grid.CellPosition;

public class GridTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(10, 10);
        board.initializeboard();
    }

    @Test
    void testSetAndGet() {

        CellPosition position1 = new CellPosition(0, 1);
        CellPosition position2 = new CellPosition(9, 5);
        CellPosition position3 = new CellPosition(6, 7);

        board.set(position1, 'S');
        board.set(position2, 'R');
        board.set(position3, 'P');

        assertEquals('S', board.get(position1));
        assertEquals('R', board.get(position2));
        assertEquals('P', board.get(position3));
    }

    @Test
    void testSetOnBoundary() {

        board.set(new CellPosition(0, 0), 'X');
        board.set(new CellPosition(9, 9), 'Y');

        assertEquals('X', board.get(new CellPosition(0, 0)));
        assertEquals('Y', board.get(new CellPosition(9, 9)));
    }

    @Test
    void testSetOutsideGrid() {

        CellPosition outOfBounds = new CellPosition(10, 10);

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            board.set(outOfBounds, 'Z');
        });

        assertTrue(exception.getMessage().contains("Cant set a position not on the grid"));
    }

}
