package no.uib.inf101.brick;

import javax.swing.JFrame;

import no.uib.inf101.brick.controller.GameController;
import no.uib.inf101.brick.model.Board;
import no.uib.inf101.brick.model.GameModel;
import no.uib.inf101.brick.view.GameView;

public class Main {
  public static final String WINDOW_TITLE = "BuildSim";

  public static void main(String[] args) {
    Board board = new Board(25, 50);
    GameModel gameModel = new GameModel(board);
    GameView gameView = new GameView(board, gameModel);

    GameController GameController = new GameController(gameView, gameModel);

    JFrame frame = new JFrame("BuildSim");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setContentPane(gameView);

    frame.pack();
    frame.setVisible(true);
  }

}
