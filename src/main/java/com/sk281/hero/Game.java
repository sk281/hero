package com.sk281.hero;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen; // Class field
    private int x = 10; // X coordinate for drawing
    private int y = 10; // Y coordinate for drawing
    private boolean exitLoop = false; // Control variable for the game loop

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal); // Assign to class field
            screen.setCursorPosition(null); // We don't need a cursor
            screen.startScreen(); // Screens must be started
            screen.doResizeIfNecessary(); // Resize screen if necessary
            screen.clear(); // Clear the screen for the initial draw
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        System.out.println(key); // Print the pressed key

        // Handle specific keys
        switch (key.getKeyType()) {
            case ArrowUp:
                y--; // Move up
                break;
            case ArrowDown:
                y++; // Move down
                break;
            case ArrowLeft:
                x--; // Move left
                break;
            case ArrowRight:
                x++; // Move right
                break;
            case Character:
                if (key.getCharacter() == 'q') {
                    exitLoop = true; // Set exitLoop to true
                }
                break;
            case EOF:
                exitLoop = true; // Set exitLoop to true if EOF is received
                break;
            default:
                break;
        }
    }

    private void draw() throws IOException {
        screen.clear(); // Clear the screen before drawing
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]); // Draw 'X' at (x, y)
        screen.refresh(); // Refresh the screen to show changes
    }

    public void run() throws IOException {
        while (!exitLoop) { // Run while exitLoop is false
            draw(); // Call the draw method to render the game state

            KeyStroke keyStroke = screen.readInput(); // Read a key stroke
            processKey(keyStroke); // Process the key stroke
        }

        // Close the screen after exiting the loop
        try {
            screen.close(); // Close the screen to clean up resources
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO exceptions on closing
        }

        System.out.println("Game exited."); // Print exit message
    }
}
