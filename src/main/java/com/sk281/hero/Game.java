package com.sk281.hero;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.sk281.hero.Hero;
import com.sk281.hero.Position;

import java.io.IOException;

public class Game {
    private Screen screen; // Class field for the screen
    private Hero hero; // Class field for the hero
    private boolean exitLoop = false; // Control variable for the game loop

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

    private void processKey(KeyStroke key) {
        Position newPosition = hero.getPosition(); // Get the current position

        switch (key.getKeyType()) {
            case ArrowUp:
                newPosition = hero.moveUp(); // Get new position after moving up
                break;
            case ArrowDown:
                newPosition = hero.moveDown(); // Get new position after moving down
                break;
            case ArrowLeft:
                newPosition = hero.moveLeft(); // Get new position after moving left
                break;
            case ArrowRight:
                newPosition = hero.moveRight(); // Get new position after moving right
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

        // Move the hero to the new position
        moveHero(newPosition);
    }

    // Method to move the hero to the new position
    private void moveHero(Position newPosition) {
        // Update the hero's position
        hero.getPosition().setX(newPosition.getX());
        hero.getPosition().setY(newPosition.getY());
    }

    private void draw() throws IOException {
        screen.clear(); // Clear the screen before drawing
        hero.draw(screen); // Draw the hero on the screen
        screen.refresh(); // Refresh the screen to show the updated hero position
    }

    public void run() throws IOException {
        while (!exitLoop) { // Run while exitLoop is false
            draw(); // Draw the hero on the screen

            KeyStroke keyStroke = screen.readInput(); // Read a key stroke
            processKey(keyStroke); // Process the key stroke
        }

        try {
            screen.close(); // Close the screen to clean up resources
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO exceptions on closing
        }

        System.out.println("Game exited."); // Print exit message
    }
}
