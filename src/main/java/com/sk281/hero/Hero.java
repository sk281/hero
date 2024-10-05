package com.sk281.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Hero extends Element {
    // Constructor to initialize position
    public Hero(int x, int y) {
        super(x, y); // Call the constructor of the Element class
    }

    // Move the hero up and return the new position
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1); // Return new position
    }

    // Move the hero down and return the new position
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1); // Return new position
    }

    // Move the hero left and return the new position
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY()); // Return new position
    }

    // Move the hero right and return the new position
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY()); // Return new position
    }

    @Override
    public void draw(Screen screen) throws IOException {
        // Draw 'X' at the hero's position using TextCharacter
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }


}
