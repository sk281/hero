package com.sk281.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.Random;


public class Monster extends Element {

    public Monster(int x, int y) {
        super(x, y);
    }


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

        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('W')[0]);
    }


}
