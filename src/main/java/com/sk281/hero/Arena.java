package com.sk281.hero;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TerminalPosition; // Import TerminalPosition
import com.googlecode.lanterna.TerminalSize; // Import TerminalSize
import com.googlecode.lanterna.TextColor; // Import TextColor
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    public int loop = 0;
    private List<Wall> walls;
    private List<Coin> coins;
    private Monster monster ;

    // Constructor for Arena, initializing Hero within Arena
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
        monster = new Monster(9,9);// Initial position of hero
        this.walls = createWalls();
        this.coins = createCoins();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0)); // Top wall
            walls.add(new Wall(c, height - 1)); // Bottom wall
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r)); // Left wall
            walls.add(new Wall(width - 1, r)); // Right wall
        }
        return walls;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return coins;
    }
    // Check if a position is within the arena bounds and not colliding with walls
    // Check if a position is within the arena bounds and not colliding with walls
    // Check if a position is within the arena bounds and not colliding with walls
    private boolean canHeroMove(Position position) {
        // Check if the position is within the arena bounds
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
            return false; // Out of bounds
        }

        // Check if the position collides with any wall
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                System.out.println("Can't move here: Collision with wall at " + position);
                return false; // Collision with wall
            }
        }

        // If no collision, it's a valid move
        return true; // Valid move
    }

    private void retrieveCoins() {
        for (int i = 0; i < coins.size(); i++) {

            if (coins.get(i).getPosition().equals(hero.getPosition())) {

                coins.remove(i); // Remove the coin
                break; // Exit after collecting one coin
            }
        }
    }


    // Move the hero to a new position if it's within bounds
    public void moveHero(Position newPosition) {
        if (canHeroMove(newPosition)) {
            hero.setPosition(newPosition);
        }
        retrieveCoins();
        monster.move();
    }

    // Method to process keys to move hero
    // Method to process keys to move hero
    public void processKey(KeyStroke key) {
        Position newPosition = hero.getPosition();

        System.out.println("Current Position: " + hero.getPosition());

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

        System.out.println("New Position: " + newPosition); // Print the new position

        moveHero(newPosition); // Move the hero after processing the key
    }


    // Draws the arena floor and hero on the screen
    public void draw(Screen screen) throws IOException {
        TextGraphics graphics = screen.newTextGraphics(); // Create TextGraphics object
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699")); // Set background color
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' '); // Fill rectangle

        hero.draw(screen); // Draw the hero
        for (Wall wall : walls) {
            wall.draw(screen); // Draw each wall
        }
        for (Coin coin : coins){
            coin.draw(screen);
        }

        monster.draw(screen);
    }
}
