// package no.uib.inf101.Game.ViewTest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.Test;

// import no.uib.inf101.brick.model.Board;
// import no.uib.inf101.brick.model.GameModel;
// import no.uib.inf101.brick.view.DefaultColorTheme;
// import no.uib.inf101.brick.view.GameView;
// import no.uib.inf101.grid.CellPosition;

// import java.awt.Color;
// import java.awt.event.MouseEvent;

// public class ToggleCellColorTest {

// @Test
// public void testToggleCellColor() {
// // Arrange
// Board board = new Board(1, 1);
// GameModel gameModel = new GameModel(board);
// GameView gameView = new GameView(board, gameModel);
// DefaultColorTheme defaultColorTheme = new DefaultColorTheme();
// gameView.cellSize = 20;
// gameView.paletteHeight = 30;

// MouseEvent mockEvent = new MouseEvent(gameView, MouseEvent.MOUSE_CLICKED,
// System.currentTimeMillis(), 0, 10, 35,
// 1, false);
// Color expectedColor = new Color(135, 206, 235);

// gameView.toggleCellColor(mockEvent);

// CellPosition position = new CellPosition(0, 0);
// Color actualColor = defaultColorTheme.getCellColor(board.get(position));
// assertEquals(expectedColor, actualColor, "The color of the cell should match
// the expected color after click.");
// }
// }
