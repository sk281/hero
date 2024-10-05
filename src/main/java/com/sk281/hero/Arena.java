package com.sk281.hero;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TextCharacter; // Import the TextCharacter class
import com.googlecode.lanterna.TerminalPosition; // Import TerminalPosition
import com.googlecode.lanterna.TerminalSize; // Import TerminalSize
import com.googlecode.lanterna.TextColor; // Import TextColor
import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    public int loop = 0;

    // Constructor for Arena, initializing Hero within Arena
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10); // Initial position of hero
    }

    // Check if a position is within the arena bounds
    private boolean canHeroMove(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    // Move the hero to a new position if it's within bounds
    public void moveHero(Position newPosition) {
        if (canHeroMove(newPosition)) {
            hero.setPosition(newPosition);
        }
    }

    // Method to process keys to move hero
    public void processKey(KeyStroke key) {
        Position newPosition = hero.getPosition();

        switch (key.getKeyType()) {
            case ArrowUp -> newPosition = hero.moveUp();
            case ArrowDown -> newPosition = hero.moveDown();
            case ArrowLeft -> newPosition = hero.moveLeft();
            case ArrowRight -> newPosition = hero.moveRight();
            case Character -> {
                if (key.getCharacter() == 'q') {
                    loop = 1; // Exit loop when 'q' is pressed
                    return; // Exit method without moving the hero
                }
            }
            case EOF -> {
                loop = 1; // Exit loop on EOF
                return; // Exit method without moving the hero
            }
            default -> {}
        }

        moveHero(newPosition); // Move the hero after processing the key
    }

    // Draws the arena floor and hero on the screen
    public void draw(Screen screen) throws IOException {
        TextGraphics graphics = screen.newTextGraphics(); // Create TextGraphics object
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699")); // Set background color
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' '); // Fill rectangle

        hero.draw(screen); // Draw the hero
    }
}
