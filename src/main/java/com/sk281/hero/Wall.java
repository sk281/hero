package com.sk281.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Wall {

    private Position position; // Position of the hero

    // Constructor to initialize position
    public Wall(int x, int y) {
        this.position = new Position(x, y);
    }

    // Getter for Position
    public Position getPosition() {
        return position;
    }


    public void draw(Screen screen) throws IOException {
        // Draw 'X' at the hero's position using TextCharacter
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('O')[0]);
    }
}

