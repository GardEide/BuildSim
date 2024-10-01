package no.uib.inf101.brick.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import no.uib.inf101.brick.model.Board;
import no.uib.inf101.brick.model.GameModel;
import no.uib.inf101.brick.model.GameState;
import no.uib.inf101.grid.CellPosition;

public class GameView extends JPanel {
    private Board board;
    public static final int CELLSIZE = 30;
    private static final int GRASS_HEIGHT = CELLSIZE * 2;
    public static final int PALETTE_HEIGHT = GRASS_HEIGHT;
    private DefaultColorTheme materialTheme = new DefaultColorTheme();
    private char[] paletteChars = { 'S', 'W', 'M', 'P', 'B', 'G', 'D', '-' };
    private GameModel gameModel;
    private char selectedCharacter = 'S';
    private JButton gravityButton;
    private JButton resetButton;

    private Image welcomeScreen = materialTheme.getWelcomeScreenImage();

    public GameView(Board board, GameModel gameModel) {
        this.board = board;
        this.gameModel = gameModel;

        setPreferredSize(
                new Dimension(CELLSIZE * board.cols(), (CELLSIZE * board.rows()) + GRASS_HEIGHT));
        setFocusable(true);
        initUI();

    }

    private void initUI() {
        // creates the buttons for reset and gravity
        // first part taken from chatGPT to give me the custom font
        try {

            InputStream is = getClass().getResourceAsStream("/Minecraft.ttf");
            Font minecraftFont = Font.createFont(Font.TRUETYPE_FONT, is);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(minecraftFont);

        } catch (IOException | FontFormatException e) {

            e.printStackTrace();
        }
        // sets the layout of the buttons with custom font
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        gravityButton = new JButton("GRAVITY ON");
        resetButton = new JButton("RESET");

        Font pixelFont = new Font("Minecraft", Font.PLAIN, 13);

        gravityButton.setFont(pixelFont);
        resetButton.setFont(pixelFont);

        gravityButton.setBackground(Color.BLACK);
        gravityButton.setForeground(Color.WHITE);
        resetButton.setBackground(Color.BLACK);
        resetButton.setForeground(Color.WHITE);

        gravityButton.setOpaque(true);
        gravityButton.setBorderPainted(false);
        resetButton.setOpaque(true);
        resetButton.setBorderPainted(false);

        gravityButton.addActionListener(e -> toggleGravity());
        resetButton.addActionListener(e -> {
            board.initializeboard();
            repaint();
        });

        buttonPanel.add(gravityButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.NORTH);

    }

    // toggles gravity and changes button text
    private void toggleGravity() {
        if (gameModel.isGravityStarted()) { //
            gameModel.stopGravity();
            gravityButton.setText("GRAVITY ON");
        } else {
            gameModel.startGravity();
            gravityButton.setText("GRAVITY OFF");
            gameModel.applyGravity();
            repaint();
        }
    }

    @Override
    // Paints the different layers
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawCells(g);
        drawGrassCells(g);
        drawColorPalette(g);
        // draws the welcome screen only when gamestate is WELCOME_SCREEN
        if (gameModel.getGameState().equals(GameState.WELCOME_SCREEN)) {
            drawWelcomeScreen(g);
        }
    }

    private void drawWelcomeScreen(Graphics g) {
        // first part taken from chatGPT, changes the font to a custom one
        try {

            InputStream is = getClass().getResourceAsStream("/Minecraft.ttf");
            Font minecraftFont = Font.createFont(Font.TRUETYPE_FONT, is);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(minecraftFont);

        } catch (IOException | FontFormatException e) {

            e.printStackTrace();
        }
        // draws instructions for starting the game
        g.drawImage(welcomeScreen, 0, 0, getWidth(), getHeight(), this);

        Font pixelFont = new Font("Minecraft", Font.PLAIN, 20);
        g.setFont(pixelFont);
        g.setColor(Color.WHITE);

        FontMetrics metrics = g.getFontMetrics(pixelFont);
        int x = (getWidth() - metrics.stringWidth("Press SPACE to play!")) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();

        g.drawString("Press SPACE to play", x, y);
    }

    // draws the initialized board with the standard color
    private void drawCells(Graphics g) {
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                CellPosition position = new CellPosition(row, col);
                char character = board.get(position);
                Image texture = materialTheme.getTexture(character);
                g.drawImage(texture, col * CELLSIZE, row * CELLSIZE, CELLSIZE, CELLSIZE, this);

            }
        }
    }

    private void drawGrassCells(Graphics g) {
        g.setColor(new Color(124, 252, 0));

        Image grassTexture = materialTheme.getTexture('K');
        Image dirtTexture = materialTheme.getTexture('L');

        // iterates over the grass cells drawing grass first, then dirt bellow
        for (int col = 0; col < board.cols(); col++) {

            g.drawImage(grassTexture, col * CELLSIZE, getHeight() - GRASS_HEIGHT, CELLSIZE, CELLSIZE, this);

            for (int row = 1; row < 2; row++) {
                g.drawImage(dirtTexture, col * CELLSIZE, getHeight() - GRASS_HEIGHT + (row * CELLSIZE), CELLSIZE,
                        CELLSIZE, this);
            }
        }
    }

    private void drawColorPalette(Graphics g) {

        // draws the colorpallete coresponding to available blocks
        for (int i = 0; i < paletteChars.length; i++) {
            Image texture = materialTheme.getTexture(paletteChars[i]);
            int x = (int) (CELLSIZE * 2.5 * i);
            int y = 7;

            g.setColor(Color.BLACK);
            g.fillRect(x - 2, y - 2, CELLSIZE * 2 + 4, CELLSIZE * 2 + 4);

            g.drawImage(texture, x, y, CELLSIZE * 2, CELLSIZE * 2, this);
        }

        // draws the selected block
        int x = (int) (CELLSIZE * 2.5 * paletteChars.length + 1);
        int y = 7;
        g.setColor(Color.BLACK);
        g.fillRect(x - 2, y - 2, (int) (CELLSIZE * 1.5 + 4), (int) (CELLSIZE * 1.5 + 4));

        Image selectedTexture = materialTheme.getTexture(selectedCharacter);
        g.drawImage(selectedTexture, x, y, (int) (CELLSIZE * 1.5), (int) (CELLSIZE * 1.5), this);

    }

    public void toggleCellColor(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // uses the position of the mouse to determine whether the player is selecting
        // the colorpallet or board
        if (y <= PALETTE_HEIGHT) {

            int colorIndex = x / (int) (CELLSIZE * 2.5);
            if (colorIndex >= 0 && colorIndex < paletteChars.length) {
                selectedCharacter = paletteChars[colorIndex];
            }
        } else {

            // if the player selects a cell, it changes the image to the selected one
            int boardX = x / CELLSIZE;
            int boardY = (y - PALETTE_HEIGHT) / CELLSIZE;

            CellPosition position = new CellPosition(boardY + 2, boardX);
            if (board.positionIsOnGrid(position)) {
                board.set(position, selectedCharacter);
                if (gameModel.applyGravity()) {
                    repaint();
                }
            }
        }
        repaint();
    }

}
