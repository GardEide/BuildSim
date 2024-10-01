// package no.uib.inf101.Game;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;

// import no.uib.inf101.brick.view.DefaultColorTheme;

// import java.awt.Color;

// public class DefaultColorThemeTest {

// @Test
// public void testGetCellColor() {
// DefaultColorTheme theme = new DefaultColorTheme();

// // Test for each defined character
// assertEquals(new Color(140, 141, 141), theme.getCellColor('S'), "Incorrect
// color for 'S'");
// assertEquals(new Color(161, 102, 47), theme.getCellColor('W'), "Incorrect
// color for 'W'");
// assertEquals(new Color(113, 121, 126), theme.getCellColor('M'), "Incorrect
// color for 'M'");
// assertEquals(Color.PINK.brighter(), theme.getCellColor('P'), "Incorrect color
// for 'P'");
// assertEquals(Color.YELLOW.brighter(), theme.getCellColor('R'), "Incorrect
// color for 'R'");
// assertEquals(new Color(135, 206, 250, 123), theme.getCellColor('I'),
// "Incorrect color for 'I'");
// assertEquals(new Color(101, 67, 33, 255), theme.getCellColor('D'), "Incorrect
// color for 'D'");
// assertEquals(new Color(135, 206, 235), theme.getCellColor('-'), "Incorrect
// color for '-'");

// // Test for unsupported character
// Exception exception = assertThrows(IllegalArgumentException.class, () -> {
// theme.getCellColor('X');
// }, "Expected IllegalArgumentException for unsupported character");
// assertTrue(exception.getMessage().contains("No available color for 'X'"));
// }
// }
