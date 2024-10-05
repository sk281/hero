package com.sk281.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.Random;


public class Monster extends Element {
    // Constructor to initialize position
    public Monster(int x, int y) {
        super(x, y); // Call the constructor of the Element class
    }

    // Move the hero up and return the new position
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1); // Return new position
    }

    public void move() {
        Random random = new Random();


        int XorY = random.nextInt(2); // 0 or 1
        int deltaX = 0;
        int deltaY = 0;

        if (XorY == 1) {

            deltaX = random.nextBoolean() ? 1 : -1; // Randomly set deltaX to 1 or -1
        } else {

            deltaY = random.nextBoolean() ? 1 : -1; // Randomly set deltaY to 1 or -1
        }


        position = new Position(position.getX() + deltaX, position.getY() + deltaY);
    }


    @Override
    public void draw(Screen screen) throws IOException {
        // Draw 'X' at the hero's position using TextCharacter
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('W')[0]);
    }


}
