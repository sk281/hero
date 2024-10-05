package com.sk281.hero;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Game {
    private Screen screen; // Class field for the screen
    private Hero hero; // Class field for the hero
    private boolean exitLoop = false; // Control variable for the game loop

    // Constructor to initialize the game
    public Game() {
        try {
            // Initialize hero object at position (10, 10)
            hero = new Hero(10, 10);

            // Initialize terminal and screen
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            screen = new TerminalScreen(terminalFactory.createTerminal());
            screen.setCursorPosition(null); // We don't need a cursor
            screen.startScreen(); // Screens must be started
            screen.clear(); // Clear the screen for the initial draw
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Process the keys for moving the hero and exiting
    private void processKey(KeyStroke key) {
        // Handle specific keys for hero movement
        switch (key.getKeyType()) {
            case ArrowUp:
                hero.moveUp(); // Move hero up
                break;
            case ArrowDown:
                hero.moveDown(); // Move hero down
                break;
            case ArrowLeft:
                hero.moveLeft(); // Move hero left
                break;
            case ArrowRight:
                hero.moveRight(); // Move hero right
                break;
            case Character:
                if (key.getCharacter() == 'q') {
                    exitLoop = true; // Set exitLoop to true when 'q' is pressed
                }
                break;
            case EOF:
                exitLoop = true; // Set exitLoop to true if EOF is received
                break;
            default:
                break;
        }
    }

    // Draw the hero on the screen
    private void draw() throws IOException {
        screen.clear(); // Clear the screen before drawing
        hero.draw(screen); // Draw the hero on the screen
        screen.refresh(); // Refresh the screen to show the updated hero position
    }

    // Run the game loop
    public void run() throws IOException {
        while (!exitLoop) { // Run while exitLoop is false
            draw(); // Draw the hero on the screen

            KeyStroke keyStroke = screen.readInput(); // Read a key stroke
            processKey(keyStroke); // Process the key stroke
        }

        // Close the screen after exiting the loop
        try {
            screen.close(); // Close the screen to clean up resources
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO exceptions on closing
        }

    }
}
