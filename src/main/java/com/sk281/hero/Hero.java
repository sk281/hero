package com.sk281.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Hero extends Element {

    public Hero(int x, int y) {

        super(x, y);

    }



    public Position moveUp() {

        return new Position(position.getX(), position.getY() - 1);


    }


    public Position moveDown() {

        return new Position(position.getX(), position.getY() + 1);

    }


    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY()); // Return new position


    }


    public Position moveRight() {

        return new Position(position.getX() + 1, position.getY());
    }

    @Override
    public void draw(Screen screen) throws IOException {


        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }




}
