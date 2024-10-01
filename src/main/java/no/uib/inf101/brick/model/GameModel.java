package no.uib.inf101.brick.model;

import no.uib.inf101.brick.controller.ControllableGameModel;
import no.uib.inf101.brick.view.ViewableGameModel;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public class GameModel implements ViewableGameModel, Physics, ControllableGameModel {
    private Board board;
    private GameState gameState;

    public GameModel(Board board) {
        this.board = board;
        this.gameState = GameState.WELCOME_SCREEN;

    }

    @Override
    public GridDimension getDimension() {
        return this.board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.board;
    }

    // starts the game, leaves the welcome screen
    public void startGame() {
        this.gameState = GameState.ACTIVE_GAME;
    }

    // gets the current gamestate
    public GameState getGameState() {
        return this.gameState;
    }

    // starts gravity
    public void startGravity() {
        gameState = GameState.SIM_ON;

    }

    // stops gravity
    public void stopGravity() {
        gameState = GameState.SIM_OFF;
    }

    // checks if gravity is on
    public boolean isGravityStarted() {
        return gameState == GameState.SIM_ON;
    }

    public boolean applyGravity() {
        if (!isGravityStarted()) {
            return false;
        }
        boolean moved = false;

        for (int row = board.rows() - 2; row >= 0; row--) {
            for (int col = 0; col < board.cols(); col++) {
                CellPosition current = new CellPosition(row, col);
                CellPosition below = new CellPosition(row + 1, col);

                // Check if the cell directly below the current one is empty and if current is
                // not empty
                if (!board.get(current).equals('-') && board.get(below).equals('-')) {
                    boolean hasSupport = false;

                    // Check for support on both sides unless its on the edge
                    if (col > 0 && col < board.cols() - 1) {
                        CellPosition left = new CellPosition(row, col - 1);
                        CellPosition right = new CellPosition(row, col + 1);
                        hasSupport = (!board.get(left).equals('-') && !board.get(right).equals('-'));
                    }

                    // If no support on both sides, let the block fall
                    if (!hasSupport) {
                        board.set(below, board.get(current));
                        board.set(current, '-');
                        moved = true;
                    }
                }
            }
        }

        return moved; // Return true if any block moved during this tick
    }

    @Override
    // sets the speed which the block fall, lower number, stronger gravity
    public int getTimeInMilliseconds() {
        return 100;
    }

    @Override
    // applys gravity every clock tick
    public void clockTick() {
        if (isGravityStarted()) {
            applyGravity();
        }

    }

}
