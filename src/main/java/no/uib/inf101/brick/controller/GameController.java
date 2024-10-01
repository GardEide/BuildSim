package no.uib.inf101.brick.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import no.uib.inf101.brick.model.GameModel;
import no.uib.inf101.brick.view.GameView;

public class GameController implements MouseListener, KeyListener {
    private GameView gameView;
    private GameModel gameModel;
    private Timer timer;

    public GameController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;
        this.timer = new Timer(gameModel.getTimeInMilliseconds(), this::clockTick); // Kode brukt fra tetris oppgave
        this.timer.start();
        gameView.addMouseListener(this);
        gameView.addKeyListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gameView.toggleCellColor(e);
    }

    public void clockTick(ActionEvent actionEvent) {
        // kode brukt fra tetris opggaven
        if (gameModel.isGravityStarted()) {
            gameModel.applyGravity();
            clockTickhelpTimer();
            gameView.repaint();

        }
    }

    public void clockTickhelpTimer() {
        // kode brukt fra tetris opggaven
        int delay = gameModel.getTimeInMilliseconds();
        this.timer.setDelay(delay);
        this.timer.setInitialDelay(delay);
        ;

    }

    public void mouseClicked(MouseEvent e) {
        // gameView.toggleCellColor(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //"Starter spillet" setter gamestate til Active
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameModel.startGame();
            gameView.repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

}
