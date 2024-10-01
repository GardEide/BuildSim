package no.uib.inf101.brick.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DefaultColorTheme implements MaterialTheme {
    private final Image stoneTexture;
    private final Image woodTexture;
    private final Image metalTexture;
    private final Image brickTexture;
    private final Image diamondTexture;
    private final Image granitTexture;
    private final Image skyTexture;
    private final Image plankTexture;
    private final Image dirtTexture;
    private final Image grassTexture;

    public DefaultColorTheme() {
        // images of the different blocks
        stoneTexture = new ImageIcon(getClass().getResource("/stoneTexture.png")).getImage();
        woodTexture = new ImageIcon(getClass().getResource("/woodTexture.png")).getImage();
        metalTexture = new ImageIcon(getClass().getResource("/metalTexture.png")).getImage();
        brickTexture = new ImageIcon(getClass().getResource("/brickTexture.png")).getImage();
        diamondTexture = new ImageIcon(getClass().getResource("/diamondTexture.png")).getImage();
        granitTexture = new ImageIcon(getClass().getResource("/granitTexture.png")).getImage();
        skyTexture = new ImageIcon(getClass().getResource("/skyTexture.png")).getImage();
        plankTexture = new ImageIcon(getClass().getResource("/plankTexture.png")).getImage();
        grassTexture = new ImageIcon(getClass().getResource("/grassTexture.png")).getImage();
        dirtTexture = new ImageIcon(getClass().getResource("/dirtTexture.png")).getImage();
    }

    public Image getTexture(char character) {
        // returns the correct image based on character
        return switch (character) {
            case 'S' -> stoneTexture;
            case 'W' -> woodTexture;
            case 'M' -> metalTexture;
            case 'P' -> plankTexture;
            case 'B' -> brickTexture;
            case 'G' -> granitTexture;
            case 'D' -> diamondTexture;
            case 'K' -> grassTexture;
            case 'L' -> dirtTexture;
            case '-' -> skyTexture;
            default -> throw new IllegalArgumentException(
                    "No available color for '" + character + "'");
        };
    }

    // returns the color of the frame

    // returns the background color
    @Override
    public Color getStandardBackgroundColor() {
        return new Color(135, 206, 235);
    }

    public Image getWelcomeScreenImage() {
        return new ImageIcon(getClass().getResource("/welcomeScreenTexture.png")).getImage();
    }

}
