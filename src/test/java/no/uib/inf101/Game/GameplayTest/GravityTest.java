package no.uib.inf101.Game.GameplayTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.brick.model.Board;
import no.uib.inf101.brick.model.GameModel;
import no.uib.inf101.grid.CellPosition;

import static org.junit.jupiter.api.Assertions.*;

class GravityTest {
    private GameModel gameModel;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(10, 10);
        gameModel = new GameModel(board);
        gameModel.startGravity();
    }

    @Test
    void testApplyGravitySingleBlockFalls() {

        board.set(new CellPosition(8, 5), 'S');

        gameModel.applyGravity();

        assertEquals('-', board.get(new CellPosition(8, 5)), "The original position should be empty after gravity.");
        assertEquals('S', board.get(new CellPosition(9, 5)), "The block should have moved down one position.");
    }

    @Test
    // Testing case where block is supported by adjacent blocks
    void testApplyGravityWithSupport() {

        board.set(new CellPosition(9, 4), 'S');
        board.set(new CellPosition(8, 4), 'S');
        board.set(new CellPosition(9, 6), 'S');
        board.set(new CellPosition(8, 6), 'S');

        board.set(new CellPosition(7, 5), 'S'); // The falling block

        gameModel.applyGravity();

        assertEquals('S', board.get(new CellPosition(8, 5)));
        assertEquals('-', board.get(new CellPosition(9, 5))); // Empty block bellow the supported one
    }

    @Test
    // Testing that blocks shouldn't be supported by the edge
    void testApplyGravityWithoutSupportOnEdges() {

        board.set(new CellPosition(8, 0), 'S');
        board.set(new CellPosition(9, 0), '-');

        gameModel.applyGravity();

        assertEquals('-', board.get(new CellPosition(8, 0)));
        assertEquals('S', board.get(new CellPosition(9, 0)));
    }
}
