package no.uib.inf101.brick.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public interface ViewableGameModel {

    /**
     * Returns the dimensions of the game board.
     * 
     * @return The dimensions of the game board
     */
    GridDimension getDimension();

    /**
     * Iterates over all the tiles on the game board.
     * Specifically, a method that returns an object that, when iterated over,
     * provides all the positions on the board along with their corresponding
     * symbols.
     * 
     * @return The positions on the board along with their corresponding symbols
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

}
