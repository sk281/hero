package com.sk281.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Hero {
    private int x; // X position
    private int y; // Y position

    // Constructor to initialize position
    public Hero(int posx, int posy) {
        this.x = posx;
        this.y = posy;
    }

    // Getter for X position
    public int getX() {
        return x;
    }

    // Setter for X position
    public void setX(int x) {
        this.x = x;
    }

    // Getter for Y position
    public int getY() {
        return y;
    }

    // Setter for Y position
    public void setY(int y) {
        this.y = y;
    }

    // Movement methods
    public void moveUp() {
        y--; // Move up
    }

    public void moveDown() {
        y++; // Move down
    }

    public void moveLeft() {
        x--; // Move left
    }

    public void moveRight() {
        x++; // Move right
    }

    // Method to draw the hero on the given screen
    public void draw(Screen screen) throws IOException {
        // Draw 'X' at the hero's position using TextCharacter
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }
}
